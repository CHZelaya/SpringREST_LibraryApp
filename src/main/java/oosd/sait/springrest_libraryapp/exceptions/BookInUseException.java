package oosd.sait.springrest_libraryapp.exceptions;

public class BookInUseException extends RuntimeException {
    public BookInUseException(String message) {
        super(message);
    }
}
