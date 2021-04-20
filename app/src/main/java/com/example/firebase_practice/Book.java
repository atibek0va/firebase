package com.example.firebase_practice;

public class Book {
//    Object photo;
    String title;
    String author;
    String score;
    String pages;

    public Book(){}

    public Book(String title, String author, String score, String pages) {
//        this.photo = photo;
        this.title = title;
        this.author = author;
        this.score = score;
        this.pages = pages;
    }

//    public Object getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
