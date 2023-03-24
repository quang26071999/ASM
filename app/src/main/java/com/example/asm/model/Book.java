package com.example.asm.model;

import java.util.List;

public class Book {
    public String bookName,description,authorName,year,coverImage;
    List<String> images;

    public Book(String bookName, String description, String authorName, String year, String coverImage, List<String> images) {
        this.bookName = bookName;
        this.description = description;
        this.authorName = authorName;
        this.year = year;
        this.coverImage = coverImage;
        this.images = images;
    }
}
