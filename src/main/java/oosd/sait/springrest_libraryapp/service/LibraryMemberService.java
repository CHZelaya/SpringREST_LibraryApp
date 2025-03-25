package oosd.sait.springrest_libraryapp.service;


import jakarta.transaction.Transactional;
import oosd.sait.springrest_libraryapp.entities.LibraryMember;

import oosd.sait.springrest_libraryapp.entities.MembershipCard;
import oosd.sait.springrest_libraryapp.exceptions.InUseException;
import oosd.sait.springrest_libraryapp.exceptions.NotFoundException;
import oosd.sait.springrest_libraryapp.repository.BorrowRecordRepo;
import oosd.sait.springrest_libraryapp.repository.LibraryMemberRepo;
import oosd.sait.springrest_libraryapp.repository.MembershipCardRepo;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
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

    /**
     * A method that will create a LibraryMember.
     * @param libraryMember
     */
    public void createLibraryMember(LibraryMember libraryMember) {
        // Create a new MembershipCard
//        MembershipCard membershipCard = new MembershipCard();
//
//        // Set the issue date to today
//        membershipCard.setIssueDate(new Loc());
//
//        // Set the expiry date to ten years from now
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(membershipCard.getIssueDate());
//        calendar.add(Calendar.YEAR, 10); // Add 10 years
//        membershipCard.setExpiryDate(calendar.getTime());
//
//        // Assign the MembershipCard to the LibraryMember
//        libraryMember.setMembershipCard(membershipCard);

        // Save the LibraryMember (MembershipCard will be saved due to cascade)
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
     * @throws NotFoundException if no LibraryMember with the given ID exists.
     */
    public LibraryMember getLibraryMember(long id) {
        return libraryMemberRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "Library Member not found"));
    }

    /**
     * The method responsible for updating a Library Member fetched by ID.
     * This method updates the details of an existing LibraryMember, including
     * their name, email, membership card, and borrow records.
     *
     * @param id The ID of the LibraryMember to be updated.
     * @param libraryMember An object containing the updated information for the LibraryMember.
     * @return Returns the updated LibraryMember object after saving it to the database.
     * @throws NotFoundException if no LibraryMember with the given ID exists.
     */
    public LibraryMember updateLibraryMember(long id, LibraryMember libraryMember) {
        LibraryMember existingMember = libraryMemberRepo.findById(id).orElseThrow(() -> new NotFoundException(id, "LibraryMember not found"));
            existingMember.setName(libraryMember.getName());
            existingMember.setEmail(libraryMember.getEmail());
            existingMember.setMembershipCard(libraryMember.getMembershipCard());
            existingMember.getBorrowRecords().clear();
            existingMember.getBorrowRecords().addAll(libraryMember.getBorrowRecords());
            return libraryMemberRepo.save(existingMember);
    }

    /**
     * The method responsible for deleting a LibraryMember from the database.
     * Before deletion, it checks if the LibraryMember has any borrowed books.
     * If there are borrowed books, an exception is thrown, informing the user
     * that the LibraryMember must return all books before the account can be closed.
     *
     * @param libraryMemberId The ID of the LibraryMember to be deleted.
     * @throws NotFoundException if no LibraryMember with the given ID exists.
     * @throws InUseException if the LibraryMember has borrowed books and cannot be deleted.
     */
    public void deleteLibraryMember(long libraryMemberId) {
        LibraryMember existingMember = libraryMemberRepo.findById(libraryMemberId).orElseThrow(() -> new NotFoundException(libraryMemberId, "LibraryMember not found"));
        if (!existingMember.getBorrowRecords().isEmpty()){
            throw new InUseException(libraryMemberId, "Library Member is currently borrowing a book, Please return all books before removing." );
        }
        libraryMemberRepo.delete(existingMember);

    }

} //class
