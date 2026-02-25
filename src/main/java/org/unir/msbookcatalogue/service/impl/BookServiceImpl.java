package org.unir.msbookcatalogue.service.impl;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.unir.msbookcatalogue.dto.BookResponse;
import org.unir.msbookcatalogue.dto.CreateBookRequest;
import org.unir.msbookcatalogue.dto.UpdateBookRequest;
import org.unir.msbookcatalogue.dto.SearchResponse;
import org.unir.msbookcatalogue.model.Book;
import org.unir.msbookcatalogue.repository.BookRepository;
import org.unir.msbookcatalogue.service.BookService;
import org.unir.msbookcatalogue.exception.BookNotFoundException;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationBuilders;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public BookServiceImpl(BookRepository bookRepository, ElasticsearchOperations elasticsearchOperations) {
        this.bookRepository = bookRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public BookResponse createBook(CreateBookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publicationDate(request.getPublicationDate())
                .category(request.getCategory())
                .isbn(request.getIsbn())
                .rating(request.getRating())
                .price(request.getPrice())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .visible(request.getVisible() != null ? request.getVisible() : true)
                .build();

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateBook(String id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationDate(request.getPublicationDate());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setRating(request.getRating());
        book.setPrice(request.getPrice());
        book.setDescription(request.getDescription());
        book.setImageUrl(request.getImageUrl());
        book.setVisible(request.getVisible());

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse patchBook(String id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (request.getTitle() != null)
            book.setTitle(request.getTitle());
        if (request.getAuthor() != null)
            book.setAuthor(request.getAuthor());
        if (request.getPublicationDate() != null)
            book.setPublicationDate(request.getPublicationDate());
        if (request.getCategory() != null)
            book.setCategory(request.getCategory());
        if (request.getIsbn() != null)
            book.setIsbn(request.getIsbn());
        if (request.getRating() != null)
            book.setRating(request.getRating());
        if (request.getPrice() != null)
            book.setPrice(request.getPrice());
        if (request.getDescription() != null)
            book.setDescription(request.getDescription());
        if (request.getImageUrl() != null)
            book.setImageUrl(request.getImageUrl());
        if (request.getVisible() != null)
            book.setVisible(request.getVisible());

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return mapToResponse(book);
    }

    @Override
    public SearchResponse searchBooks(
            String title,
            String author,
            String publicationDate,
            String category,
            String isbn,
            Integer rating,
            Boolean visible) {

        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        if (title != null && !title.isEmpty()) {
            boolQuery.should(QueryBuilders.match(m -> m.field("title").query(title).fuzziness("AUTO")));
            boolQuery.should(QueryBuilders.match(m -> m.field("title.suggest").query(title)));
        }

        if (author != null && !author.isEmpty()) {
            boolQuery.must(QueryBuilders.match(m -> m.field("author").query(author)));
        }

        if (category != null && !category.isEmpty()) {
            boolQuery.must(QueryBuilders.term(t -> t.field("category").value(category)));
        }

        if (visible != null) {
            boolQuery.must(QueryBuilders.term(t -> t.field("visible").value(visible)));
        }

        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(boolQuery.build()._toQuery())
                .withAggregation("authors", AggregationBuilders.terms(t -> t.field("author.facet")))
                .withAggregation("categories", AggregationBuilders.terms(t -> t.field("category")))
                .build();

        SearchHits<Book> hits = elasticsearchOperations.search(searchQuery, Book.class);

        List<BookResponse> books = hits.getSearchHits().stream()
                .map(hit -> mapToResponse(hit.getContent()))
                .collect(Collectors.toList());

        Map<String, Long> authorFacets = new HashMap<>();
        Map<String, Long> categoryFacets = new HashMap<>();

        if (hits.hasAggregations()) {
            ElasticsearchAggregations aggregations = (ElasticsearchAggregations) hits.getAggregations();
            aggregations.aggregations().forEach(aggContainer -> {
                ElasticsearchAggregation agg = (ElasticsearchAggregation) aggContainer;
                Aggregate nativeAgg = agg.aggregation().getAggregate();
                if ("authors".equals(agg.aggregation().getName()) && nativeAgg != null && nativeAgg.isSterms()) {
                    nativeAgg.sterms().buckets().array()
                            .forEach(bucket -> authorFacets.put(bucket.key().stringValue(), bucket.docCount()));
                }
                if ("categories".equals(agg.aggregation().getName()) && nativeAgg != null && nativeAgg.isSterms()) {
                    nativeAgg.sterms().buckets().array()
                            .forEach(bucket -> categoryFacets.put(bucket.key().stringValue(), bucket.docCount()));
                }
            });
        }

        return SearchResponse.builder()
                .books(books)
                .authorFacets(authorFacets)
                .categoryFacets(categoryFacets)
                .build();
    }

    private BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationDate(book.getPublicationDate())
                .category(book.getCategory())
                .isbn(book.getIsbn())
                .rating(book.getRating())
                .price(book.getPrice())
                .description(book.getDescription())
                .imageUrl(book.getImageUrl())
                .visible(book.getVisible())
                .build();
    }
}