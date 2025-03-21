package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
