package oosd.sait.springrest_libraryapp.service;

import oosd.sait.springrest_libraryapp.entities.Book;
import oosd.sait.springrest_libraryapp.repository.BookRepo;

import java.util.List;

public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    /**
     * Method to get all the books from the DB
     * @return a LIST of all books
     */
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    /**\
     * Method to save a book into the db
     */
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    /**
     * Method to grab one book by id
     * @return One book, by ID
     */
    public Book getBookById(long id) {
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBookById(long id) {
        bookRepo.deleteById(id);
    }

}
