package oosd.sait.springrest_libraryapp.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super("Book was not found in the library" + message);
    }
}
