package org.unir.msbookcatalogue.dto;

import java.time.LocalDate;

public class BookResponse {
    private String id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String category;
    private String isbn;
    private Integer rating;
    private Double price;
    private String description;
    private String imageUrl;
    private Boolean visible;

    // Manual Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public BookResponse() {
    }

    public static class BookResponseBuilder {
        private String id;
        private String title;
        private String author;
        private LocalDate publicationDate;
        private String category;
        private String isbn;
        private Integer rating;
        private Double price;
        private String description;
        private String imageUrl;
        private Boolean visible;

        public BookResponseBuilder id(String id) {
            this.id = id;
            return this;
        }

        public BookResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookResponseBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookResponseBuilder publicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public BookResponseBuilder category(String category) {
            this.category = category;
            return this;
        }

        public BookResponseBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookResponseBuilder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public BookResponseBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public BookResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookResponseBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public BookResponseBuilder visible(Boolean visible) {
            this.visible = visible;
            return this;
        }

        public BookResponse build() {
            BookResponse res = new BookResponse();
            res.id = this.id;
            res.title = this.title;
            res.author = this.author;
            res.publicationDate = this.publicationDate;
            res.category = this.category;
            res.isbn = this.isbn;
            res.rating = this.rating;
            res.price = this.price;
            res.description = this.description;
            res.imageUrl = this.imageUrl;
            res.visible = this.visible;
            return res;
        }
    }

    public static BookResponseBuilder builder() {
        return new BookResponseBuilder();
    }
}
