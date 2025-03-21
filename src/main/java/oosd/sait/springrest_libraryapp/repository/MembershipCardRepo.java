package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.MembershipCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipCardRepo extends JpaRepository<MembershipCard, Long> {
}
