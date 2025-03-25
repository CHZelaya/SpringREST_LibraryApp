package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface LibraryMemberRepo extends JpaRepository<LibraryMember, Long> {
}
