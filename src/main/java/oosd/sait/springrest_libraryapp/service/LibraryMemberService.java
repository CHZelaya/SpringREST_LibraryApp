package oosd.sait.springrest_libraryapp.service;


import oosd.sait.springrest_libraryapp.entities.BorrowRecord;
import oosd.sait.springrest_libraryapp.entities.LibraryMember;

import oosd.sait.springrest_libraryapp.repository.BorrowRecordRepo;
import oosd.sait.springrest_libraryapp.repository.LibraryMemberRepo;
import oosd.sait.springrest_libraryapp.repository.MembershipCardRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryMemberService {
    private final LibraryMemberRepo libraryMemberRepo;
    private final BorrowRecordRepo borrowRecordRepo;
    private final MembershipCardRepo membershipCardRepo;

    public LibraryMemberService(LibraryMemberRepo libraryMemberRepo, BorrowRecordRepo borrowRecordRepo, MembershipCardRepo membershipCardRepo) {
        this.libraryMemberRepo = libraryMemberRepo;
        this.borrowRecordRepo = borrowRecordRepo;
        this.membershipCardRepo = membershipCardRepo;
    }

    public void createLibraryMember(LibraryMember libraryMember) {
        libraryMemberRepo.save(libraryMember);
    }

    /**
     * A method that will return all the LibraryMembers
     * @return Will return a list of all the library members in the database.
     */
    public List<LibraryMember> getAllLibraryMembers() {
        return libraryMemberRepo.findAll();
    }

    /**
     * A method that will return a single Library Members, identified by their ID
     * @param id
     * @return A single Library Member
     */
    public LibraryMember getLibraryMember(long id) {
        return libraryMemberRepo.findById(id).orElseThrow(() -> new RuntimeException("LibraryMember not found"));
    }

    /**
     * The method responsible for updating a Library Member fetched by ID
     * @param libraryMember object, id
     * @return Returns the updated information of the LibraryMember and saves it to the DB.
     */
    public LibraryMember updateLibraryMember(long id, LibraryMember libraryMember) {
        LibraryMember existingMember = libraryMemberRepo.findById(id).orElse(null);
        if (existingMember != null) {
            existingMember.setName(libraryMember.getName());
            existingMember.setEmail(libraryMember.getEmail());
        }
        assert existingMember != null;
        return libraryMemberRepo.save(existingMember);
    }

    /**
     * The method responsible for deleting a LibraryMember from the DB, checks to see that the Library Member has no
     * borrowed books before deleting, if a record of borrowed books are found, informs the user that the Library member
     * must finish returning all books before the account can be closed.
     * @param libraryMemberId
     * @param borrowRecordId
     */
    public void deleteLibraryMember(long libraryMemberId,long borrowRecordId) {
        LibraryMember existingMember = libraryMemberRepo.findById(libraryMemberId).orElse(null);
        BorrowRecord borrowRecord = borrowRecordRepo.findById(borrowRecordId).orElse(null);

        if (existingMember != null && borrowRecord == null) {
            libraryMemberRepo.delete(existingMember);
        } else if (existingMember == null) {
            throw new RuntimeException("Library Member was not found");
        } else if (borrowRecord != null) {
            throw new RuntimeException("Library Member has a record of borrowed books\n" +
                    "Please return all books before closing account.");
        }

    }

}
