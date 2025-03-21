package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryMemberRepo extends JpaRepository<LibraryMember, Long> {
}
