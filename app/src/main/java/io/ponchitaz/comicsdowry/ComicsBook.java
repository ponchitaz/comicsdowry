package io.ponchitaz.comicsdowry;

/**
 * This is a model which takes data from DB and puts it in smth like "boxes", operated by adapter.
 * Variable names have to match the nodes (let's call them info-nodes) in the DB,
 * and the name of the class matches parent node as well (parent which contains
 * the items with info-nodes, like, grandparent of info-nodes).
 * Each variable has a getter and a setter, which is also important.
 * There is also an empty method, which is also "must-have" in the model.
 */

public class ComicsBook {
    public String bookPicUrl;
    public String bookTitle;
    public String bookAuthors;
    public String bookDescription;
    public String bookPublishing;
    public String bookTags;
    public String bookId;


    public ComicsBook() {

    }

    public ComicsBook(String bookPicUrl, String bookTitle, String bookAuthors) {
        this.bookPicUrl = bookPicUrl;
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
        this.bookDescription = bookDescription;
        this.bookPublishing = bookPublishing;
        this.bookTags = bookTags;
        this.bookId = bookId;
    }

    public String getBookPicUrl() {
        return bookPicUrl;
    }

    public void setBookPicUrl(String bookPicUrl) {
        this.bookPicUrl = bookPicUrl;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(String bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookPublishing() {
        return bookPublishing;
    }

    public void setBookPublishing(String bookPublishing) {
        this.bookPublishing = bookPublishing;
    }

    public String getBookTags() {
        return bookTags;
    }

    public void setBookTags(String bookTags) {
        this.bookTags = bookTags;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

}
