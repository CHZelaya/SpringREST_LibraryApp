package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.MembershipCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipCardRepo extends JpaRepository<MembershipCard, Long> {
}
