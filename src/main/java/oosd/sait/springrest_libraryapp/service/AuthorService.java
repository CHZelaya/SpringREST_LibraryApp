package oosd.sait.springrest_libraryapp.service;

import oosd.sait.springrest_libraryapp.entities.Author;
import oosd.sait.springrest_libraryapp.repository.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    /**
     * Method to grab all the Authors from the DB
     * @return A list of all the Authors
     */
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    /**
     * Method to save Authors
     */
    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    /**
     * Method to grab an Author by ID
     * @return An author, selected by their ID
     */

    public Author getAuthorById(long id) {
        return authorRepo.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    /**
     * Delete Author selected by ID
     */

    public void deleteAuthor(long id) {
        authorRepo.deleteById(id);
    }



}//class
