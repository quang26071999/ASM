package com.example.asm.model;

import java.util.List;

public class Book {
    public String  _id,bookName,description,authorName,year,coverImage;
    List<String> images;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Book(String _id, String bookName, String description, String authorName, String year, String coverImage, List<String> images) {
        this._id = _id;
        this.bookName = bookName;
        this.description = description;
        this.authorName = authorName;
        this.year = year;
        this.coverImage = coverImage;
        this.images = images;
    }

    public Book() {
    }
}
