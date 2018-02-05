package io.ponchitaz.comicsdowry;

import java.util.ArrayList;

public class ComicsBook {
    public String bookPic;
    public String bookTitle;
    public String bookAuthors;

    public ComicsBook() {

    }

    public ComicsBook(String bookPic, String bookTitle, String bookAuthors) {
        this.bookPic=bookPic;
        this.bookTitle=bookTitle;
        this.bookAuthors=bookAuthors;
    }

    public  String getBookPic() {
        return bookPic;
    }
    public void setBookPic(String bookPic){
        this.bookPic = bookPic;
    }

    public String getBookTitle() {

        return bookTitle;
    }
    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }

    public String getBookAuthors() {
        return bookAuthors;
    }
    public void setBookAuthors(String bookAuthors){
        this.bookAuthors = bookAuthors;
    }

}
