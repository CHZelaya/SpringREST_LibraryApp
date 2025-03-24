package oosd.sait.springrest_libraryapp.service;

import jakarta.transaction.Transactional;
import oosd.sait.springrest_libraryapp.entities.Author;
import oosd.sait.springrest_libraryapp.entities.Book;
import oosd.sait.springrest_libraryapp.exceptions.InUseException;
import oosd.sait.springrest_libraryapp.exceptions.NotFoundException;
import oosd.sait.springrest_libraryapp.repository.BookRepo;
import oosd.sait.springrest_libraryapp.repository.BorrowRecordRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
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
     * @throws NotFoundException if the book is not found.
     */
    public Book getBookById(long id) {
        return bookRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Book"));
    }

    /**
     * UPDATE
     * Updates a book from the repository based on the provided book ID.
     * The book will only be updated if found,
     * which is determined by checking the borrow ID.
     * @param id The id provided by the user
     * @param book The Book object in question
     * @throws NotFoundException if the book is not found.
     */

    public void updateBook(long id,Book book) {
        Book existingBook = bookRepo.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPublicationYear(book.getPublicationYear());

            List<Author> currentAuthors = existingBook.getAuthors();
            currentAuthors.clear();
            currentAuthors.addAll(book.getAuthors());

            bookRepo.save(existingBook);
        } else {
            throw new NotFoundException(id, "Book");
        }

    }

    /**
     * DELETE
     * Deletes a book from the repository based on the provided book ID.
     * The book can only be deleted if it is not currently in use,
     * which is determined by checking the borrow record ID.
     *
     * @param bookId The ID of the book to be deleted.
     * @throws NotFoundException if the book is not found.
     * @throws InUseException if the book is currently in use and cannot be deleted.
     */
    public void deleteBookById(long bookId ) {
        Book existingBook = bookRepo.findById(bookId).orElseThrow(() -> new NotFoundException(bookId, "Book"));
        if (!existingBook.getBorrowRecordList().isEmpty()) {
            throw new InUseException(bookId, "Book");
        }
        bookRepo.delete(existingBook);
    }

}
