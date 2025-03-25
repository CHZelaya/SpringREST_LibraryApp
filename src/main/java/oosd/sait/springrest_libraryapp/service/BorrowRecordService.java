package oosd.sait.springrest_libraryapp.service;

import jakarta.transaction.Transactional;
import oosd.sait.springrest_libraryapp.entities.BorrowRecord;
import oosd.sait.springrest_libraryapp.exceptions.NotFoundException;
import oosd.sait.springrest_libraryapp.repository.BorrowRecordRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class BorrowRecordService {

    private final BorrowRecordRepo borrowRecordRepo;

public BorrowRecordService(BorrowRecordRepo borrowRecordRepo) {
    this.borrowRecordRepo = borrowRecordRepo;
}

    /**
     * CREATE
     * This method saves a BorrowRecord to the database. It can be used to create a new
     * borrowing record or update an existing one. The method returns the saved BorrowRecord
     * object, which includes any generated fields such as the ID.
     *
     * @param borrowRecord The BorrowRecord object to be saved. It must not be null.
     * @return The saved BorrowRecord object.
     * @throws IllegalArgumentException if the provided borrowRecord is null.
     */

public BorrowRecord saveBorrowRecord(BorrowRecord borrowRecord) {
    return borrowRecordRepo.save(borrowRecord);
}


/**
 * READ
 * This method is responsible for grabbing every record of BorrowRecords from the database.
 * This method returns a List of BorrowRecord objects, which includes generated values such as ID.
 * @return The saved List of BorrowRecords
 */

public List<BorrowRecord> getAllBorrowRecords() {
    return borrowRecordRepo.findAll();
}

    /**
     * READ, One Entry
     * This method retrieves a BorrowRecord from the database based on the provided ID.
     * If a BorrowRecord with the specified ID is found, it is returned; otherwise, a
     * NotFoundException is thrown indicating that the record was not found.
     *
     * @param id The ID of the BorrowRecord to be retrieved. It must not be null.
     * @return The BorrowRecord associated with the specified ID.
     * @throws NotFoundException if no BorrowRecord is found with the given ID.
     */
public BorrowRecord getBorrowRecordById(Long id) {
    return borrowRecordRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Borrow record not found"));
}

/**
 * UPDATE
 */

public void updateBorrowRecord(long id, BorrowRecord borrowRecord) {
    BorrowRecord existingBorrowRecord = borrowRecordRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Borrow record not found"));
    existingBorrowRecord.setBorrowDate(borrowRecord.getBorrowDate());
    existingBorrowRecord.setBorrower(borrowRecord.getBorrower());

}

/**
 * DELETE
 */


}//class
