package org.unir.msbookcatalogue.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.unir.msbookcatalogue.model.Book;

public class BookSpecification {

        public static Specification<Book> hasTitle(String title) {
                return (root, query, cb) -> title == null ? null
                                : cb.like(cb.lower(root.get("title")),
                                                "%" + title.toLowerCase() + "%");
        }

        public static Specification<Book> hasAuthor(String author) {
                return (root, query, cb) -> author == null ? null
                                : cb.like(cb.lower(root.get("author")),
                                                "%" + author.toLowerCase() + "%");
        }

        public static Specification<Book> hasCategory(String category) {
                return (root, query, cb) -> category == null ? null : cb.equal(root.get("category"), category);
        }

        public static Specification<Book> hasIsbn(String isbn) {
                return (root, query, cb) -> isbn == null ? null : cb.equal(root.get("isbn"), isbn);
        }

        public static Specification<Book> hasRating(Integer rating) {
                return (root, query, cb) -> rating == null ? null : cb.equal(root.get("rating"), rating);
        }

        public static Specification<Book> hasPublicationDate(String date) {
                return (root, query, cb) -> date == null ? null : cb.equal(root.get("publicationDate"), date);
        }

        public static Specification<Book> isVisible(Boolean visible) {
                return (root, query, cb) -> visible == null ? null : cb.equal(root.get("visible"), visible);
        }
}
