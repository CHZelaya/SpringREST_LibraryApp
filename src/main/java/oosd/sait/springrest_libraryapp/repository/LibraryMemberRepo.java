package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryMemberRepo extends JpaRepository<LibraryMember, Long> {
}
