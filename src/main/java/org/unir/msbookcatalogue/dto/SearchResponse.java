package org.unir.msbookcatalogue.dto;

import java.util.List;
import java.util.Map;

public class SearchResponse {
    private List<BookResponse> books;
    private Map<String, Long> authorFacets;
    private Map<String, Long> categoryFacets;

    // Manual Getters and Setters
    public List<BookResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }

    public Map<String, Long> getAuthorFacets() {
        return authorFacets;
    }

    public void setAuthorFacets(Map<String, Long> authorFacets) {
        this.authorFacets = authorFacets;
    }

    public Map<String, Long> getCategoryFacets() {
        return categoryFacets;
    }

    public void setCategoryFacets(Map<String, Long> categoryFacets) {
        this.categoryFacets = categoryFacets;
    }

    public SearchResponse() {
    }

    public static class SearchResponseBuilder {
        private List<BookResponse> books;
        private Map<String, Long> authorFacets;
        private Map<String, Long> categoryFacets;

        public SearchResponseBuilder books(List<BookResponse> books) {
            this.books = books;
            return this;
        }

        public SearchResponseBuilder authorFacets(Map<String, Long> authorFacets) {
            this.authorFacets = authorFacets;
            return this;
        }

        public SearchResponseBuilder categoryFacets(Map<String, Long> categoryFacets) {
            this.categoryFacets = categoryFacets;
            return this;
        }

        public SearchResponse build() {
            SearchResponse res = new SearchResponse();
            res.books = this.books;
            res.authorFacets = this.authorFacets;
            res.categoryFacets = this.categoryFacets;
            return res;
        }
    }

    public static SearchResponseBuilder builder() {
        return new SearchResponseBuilder();
    }
}
