package oosd.sait.springrest_libraryapp.service;

import oosd.sait.springrest_libraryapp.entities.Book;
import oosd.sait.springrest_libraryapp.entities.BorrowRecord;
import oosd.sait.springrest_libraryapp.exceptions.BookInUseException;
import oosd.sait.springrest_libraryapp.exceptions.BookNotFoundException;
import oosd.sait.springrest_libraryapp.repository.BookRepo;
import oosd.sait.springrest_libraryapp.repository.BorrowRecordRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final BorrowRecordRepo borrowRecordRepo;

    public BookService(BookRepo bookRepo, BorrowRecordRepo borrowRecordRepo) {
        this.bookRepo = bookRepo;
        this.borrowRecordRepo = borrowRecordRepo;
    }

    /**
     * Method to get all the books from the DB
     * @return a LIST of all books
     */
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    /**
     * CREATE
     *
     * Saves a new book to the repository.
     *
     * @param book The Book object to be saved.
     * @return The saved Book object, which will include generated fields such as ID.
     */
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    /**
     * READ
     * Fetches a book from the repository based on the provided book ID.
     * @return The information on the book (title, isbn, publication year, author)
     * @throws BookNotFoundException if the book is not found.
     */
    public Book getBookById(long id) {
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book ID: " + id));
    }

    /**
     * UPDATE
     * Updates a book from the repository based on the provided book ID.
     * The book will only be updated if found,
     * which is determined by checking the borrow ID.
     * @param id The id provided by the user
     * @param book The Book object in question
     * @throws BookNotFoundException if the book is not found.
     */
    public void updateBook(long id,Book book) {
        Book existingBook = bookRepo.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPublicationYear(book.getPublicationYear());
            existingBook.setAuthors(book.getAuthors());
            bookRepo.save(existingBook);
        } else {
            throw new BookNotFoundException("Book ID:" + id);
        }

    }

    /**
     * DELETE
     * Deletes a book from the repository based on the provided book ID.
     * The book can only be deleted if it is not currently in use,
     * which is determined by checking the borrow record ID.
     *
     * @param bookId The ID of the book to be deleted.
     * @param borrowRecordId The ID of the borrow record associated with the book.
     * @throws BookNotFoundException if the book is not found.
     * @throws BookInUseException if the book is currently in use and cannot be deleted.
     */
    public void deleteBookById(long bookId, long borrowRecordId ) {
        Book existingBook = bookRepo.findById(bookId).orElse(null);
        BorrowRecord existingBorrowRecord = borrowRecordRepo.findById(borrowRecordId).orElse(null);
        if (existingBook != null && existingBorrowRecord == null) {
            bookRepo.delete(existingBook);
        } else if (existingBook == null) {
            throw new BookNotFoundException("Book ID:" + bookId);
        } else {
            throw new BookInUseException("Book ID " + bookId + " is currently in use");
        }
    }

}
