package org.unir.msbookcatalogue.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unir.msbookcatalogue.dto.BookResponse;
import org.unir.msbookcatalogue.dto.CreateBookRequest;
import org.unir.msbookcatalogue.dto.UpdateBookRequest;
import org.unir.msbookcatalogue.dto.SearchResponse;
import org.unir.msbookcatalogue.service.BookService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody CreateBookRequest request) {

        BookResponse created = bookService.createBook(request);
        URI location = URI.create("/api/v1/books/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable String id,
            @Valid @RequestBody UpdateBookRequest request) {

        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> patchBook(
            @PathVariable String id,
            @RequestBody UpdateBookRequest request) {

        return ResponseEntity.ok(bookService.patchBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<SearchResponse> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publicationDate,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Boolean visible) {
        SearchResponse response = bookService.searchBooks(
                title, author, publicationDate, category, isbn, rating, visible);
        return ResponseEntity.ok(response);
    }
}
