package org.unir.msbookcatalogue.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.InnerField;

import java.time.LocalDate;

@Document(indexName = "books")
public class Book {
        @Id
        private String id;

        @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "standard"), otherFields = {
                        @InnerField(suffix = "suggest", type = FieldType.Search_As_You_Type) })
        private String title;

        @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "standard"), otherFields = {
                        @InnerField(suffix = "facet", type = FieldType.Keyword) })
        private String author;

        @Field(type = FieldType.Date)
        private LocalDate publicationDate;

        @Field(type = FieldType.Keyword)
        private String category;

        @Field(type = FieldType.Keyword)
        private String isbn;

        private Integer rating;

        @Field(type = FieldType.Double)
        private Double price;

        @Field(type = FieldType.Text)
        private String description;

        @Field(type = FieldType.Keyword)
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

        public Book() {
        }

        // Builder pattern manual (since I use it in service)
        public static class BookBuilder {
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

                public BookBuilder id(String id) {
                        this.id = id;
                        return this;
                }

                public BookBuilder title(String title) {
                        this.title = title;
                        return this;
                }

                public BookBuilder author(String author) {
                        this.author = author;
                        return this;
                }

                public BookBuilder publicationDate(LocalDate publicationDate) {
                        this.publicationDate = publicationDate;
                        return this;
                }

                public BookBuilder category(String category) {
                        this.category = category;
                        return this;
                }

                public BookBuilder isbn(String isbn) {
                        this.isbn = isbn;
                        return this;
                }

                public BookBuilder rating(Integer rating) {
                        this.rating = rating;
                        return this;
                }

                public BookBuilder price(Double price) {
                        this.price = price;
                        return this;
                }

                public BookBuilder description(String description) {
                        this.description = description;
                        return this;
                }

                public BookBuilder imageUrl(String imageUrl) {
                        this.imageUrl = imageUrl;
                        return this;
                }

                public BookBuilder visible(Boolean visible) {
                        this.visible = visible;
                        return this;
                }

                public Book build() {
                        Book book = new Book();
                        book.id = this.id;
                        book.title = this.title;
                        book.author = this.author;
                        book.publicationDate = this.publicationDate;
                        book.category = this.category;
                        book.isbn = this.isbn;
                        book.rating = this.rating;
                        book.price = this.price;
                        book.description = this.description;
                        book.imageUrl = this.imageUrl;
                        book.visible = this.visible;
                        return book;
                }
        }

        public static BookBuilder builder() {
                return new BookBuilder();
        }
}
