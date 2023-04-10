package com.example.asm.model;

import java.util.List;

public class Comment {
    private String bookId,fullName,content,date;
    private User userId;

    public Comment() {
    }

    public Comment(String bookId, String fullName, String content, String date, User userId) {
        this.bookId = bookId;
        this.fullName = fullName;
        this.content = content;
        this.date = date;
        this.userId = userId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
