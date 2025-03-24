package oosd.sait.springrest_libraryapp.exceptions;

public class BookInUseException extends RuntimeException {
    public BookInUseException(String message) {
        super("Book in use, and cannot be deleted from the library" + message);
    }
}
