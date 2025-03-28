package oosd.sait.springrest_libraryapp.service;

import jakarta.transaction.Transactional;
import oosd.sait.springrest_libraryapp.entities.Author;
import oosd.sait.springrest_libraryapp.exceptions.InUseException;
import oosd.sait.springrest_libraryapp.exceptions.NotFoundException;
import oosd.sait.springrest_libraryapp.repository.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }



    /**
     * CREATE
     * The method responsible for saving an Author into the database.
     * This method takes an Author object as input and persists it to the database using the
     * author repository. If the Author already exists, it will be updated; otherwise, a new
     * Author entry will be created. The method returns the saved Author object, which includes
     * any generated fields such as the ID.
     *
     * @param author The Author object to be saved. It must not be null.
     * @return The saved Author object, which may include an ID if it was newly created.
     * @throws IllegalArgumentException if the provided author is null.
     */
    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    /**
     * READ
     * Method to grab all the Authors from the DB
     * @return A list of all the Authors
     */
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    /**
     * READ, One entry
     * Method to grab an Author by ID
     * @return An author, selected by their ID
     */

    public Author getAuthorById(long id) {
        return authorRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Author not found"));
    }

    /**
     * UPDATE
     * Updates an Author fetched by ID.
     * This method updates the details of an existing Author, including
     * their name, biography, and books written.
     *
     * @param id The ID of the Author to be updated. Must correspond to an existing Author in the database.
     * @param author An Author object containing the updated details. Only the fields provided will be updated.
     * @return The updated Author object after saving it to the database.
     * @throws NotFoundException if the Author cannot be found in the database.
     *
     */
    public Author updateAuthor(long id,Author author) {
        Author existingAuthor = authorRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Author not found"));
        existingAuthor.setName(author.getName());
        existingAuthor.setBiography(author.getBiography());
        existingAuthor.setBooks(author.getBooks());
        return authorRepo.save(existingAuthor);
    }

    /**
     * The method responsible for deleting Authors from the database using their ID.
     * It checks if the Author has any associated books; if so, it throws an InUseException.
     * It also verifies if the Author exists in the database; if not, it throws a NotFoundException.
     * All books associated with the Author must be removed from the database before the Author can be deleted.
     * @param id The ID of the Author to be deleted. Must exist in the database.
     * @throws NotFoundException if the Author cannot be found in the database.
     * @throws InUseException if the Author has any books associated with them in the database.
     */
    public void deleteAuthor(long id) {
        Author existingAuthor = authorRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Author not found"));
        if (!existingAuthor.getBooks().isEmpty()){
            throw new InUseException(id, "Author still has books linked, please delete all books linked to author before deleting the author.");
        }
        authorRepo.deleteById(id);
    }



}//class
