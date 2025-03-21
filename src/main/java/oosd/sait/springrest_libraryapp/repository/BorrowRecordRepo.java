package oosd.sait.springrest_libraryapp.repository;

import oosd.sait.springrest_libraryapp.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {
}
