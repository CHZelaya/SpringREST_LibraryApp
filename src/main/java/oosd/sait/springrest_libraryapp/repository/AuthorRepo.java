package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
