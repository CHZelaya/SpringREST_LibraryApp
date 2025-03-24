package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {
}
