package org.unir.msbookcatalogue.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.unir.msbookcatalogue.model.Book;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
}
