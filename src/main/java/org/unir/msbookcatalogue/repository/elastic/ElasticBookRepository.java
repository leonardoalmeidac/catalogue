package org.unir.msbookcatalogue.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.unir.msbookcatalogue.model.Book;
import java.util.List;

public interface ElasticBookRepository extends ElasticsearchRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);
}
