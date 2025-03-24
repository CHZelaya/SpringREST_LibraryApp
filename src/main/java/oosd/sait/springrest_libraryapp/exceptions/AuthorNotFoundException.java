package oosd.sait.springrest_libraryapp.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Author was not found in the library");
    }
}
