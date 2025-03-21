package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
