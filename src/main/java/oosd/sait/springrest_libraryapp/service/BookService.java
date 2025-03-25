package oosd.sait.springrest_libraryapp.service;

import jakarta.transaction.Transactional;
import oosd.sait.springrest_libraryapp.entities.Author;
import oosd.sait.springrest_libraryapp.entities.Book;
import oosd.sait.springrest_libraryapp.entities.BorrowRecord;
import oosd.sait.springrest_libraryapp.exceptions.InUseException;
import oosd.sait.springrest_libraryapp.exceptions.NotFoundException;
import oosd.sait.springrest_libraryapp.repository.BookRepo;
import oosd.sait.springrest_libraryapp.repository.BorrowRecordRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * This method is responsible for updating the status of a book when it is returned.
     * It checks if the book has an associated BorrowRecord. If the record exists,
     * it updates the BorrowRecord to set the return date to the current date.
     * If the provided BorrowRecord does not belong to the book, an exception is thrown.
     *
     * @param id The ID of the book being returned.
     * @param book The Book object representing the book being returned (not used in this method).
     * @param borrowRecord The BorrowRecord object associated with the book that is being returned.
     * @throws NotFoundException if the book with the specified ID does not exist.
     * @throws IllegalArgumentException if the provided BorrowRecord does not belong to the book.
     */
    public void returnBook (long id, Book book, BorrowRecord borrowRecord) {
        Book existingBook = bookRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Book"));
        if (existingBook.getBorrowRecordList().contains(borrowRecord)){

            borrowRecord.setReturnDate(new Date());

            borrowRecordRepo.save(borrowRecord);
            bookRepo.save(existingBook);
        } else {
            throw new IllegalArgumentException("The borrow record does not belong to the book");
        }
    }


    /**
     * This method is responsible for checking out a book by updating the associated BorrowRecord.
     * It first calls isAvailable() to check if there is an existing BorrowRecord for the book.
     * If the book is not available, it throws an InUseException to alert the user.
     *
     * @param id The ID of the book to be checked out.
     * @param book The Book object representing the book being checked out (not used in this method).
     * @param borrowRecord The BorrowRecord object to be updated with the checkout details.
     * @throws InUseException if the book is currently checked out (i.e., has an active BorrowRecord).
     */
    public void checkOutBook (long id, Book book, BorrowRecord borrowRecord) {
        Book existingBook = bookRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Book"));
        if (!existingBook.isAvailable()){
            throw new InUseException(id, "Book is not available");
        }
        borrowRecord.setBook(existingBook);
        borrowRecord.setBorrowDate(new Date());
        //Set Null to returnDate to set up isAvailable method.
        borrowRecord.setReturnDate(null);

        existingBook.getBorrowRecordList().add(borrowRecord);

        borrowRecordRepo.save(borrowRecord);
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
        // Check if the book has any active borrow records
        boolean hasActiveBorrowRecords = existingBook.getBorrowRecordList().stream()
                .anyMatch(record -> record.getReturnDate() == null);

        if (hasActiveBorrowRecords) {
            throw new InUseException(bookId, "Book is currently in use and cannot be deleted.");
        }
        bookRepo.delete(existingBook);
    }

}
