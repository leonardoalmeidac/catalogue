package org.unir.msbookcatalogue.service;

import org.unir.msbookcatalogue.dto.BookResponse;
import org.unir.msbookcatalogue.dto.CreateBookRequest;
import org.unir.msbookcatalogue.dto.UpdateBookRequest;

import org.unir.msbookcatalogue.dto.SearchResponse;

public interface BookService {

    BookResponse createBook(CreateBookRequest request);

    BookResponse updateBook(String id, UpdateBookRequest request);

    BookResponse patchBook(String id, UpdateBookRequest request);

    void deleteBook(String id);

    BookResponse getBookById(String id);

    SearchResponse searchBooks(String title, String author, String publicationDate, String category, String isbn,
            Integer rating, Boolean visible);
}
