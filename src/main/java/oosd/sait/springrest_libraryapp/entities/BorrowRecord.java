package oosd.sait.springrest_libraryapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="borrow_record")
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "libraryMember_id")
    private LibraryMember libraryMember;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date borrowDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;

//    public BorrowRecord(LibraryMember libraryMember, Date borrowDate, Date returnDate, LibraryMember borrower, Book book) {
//        this.libraryMember = libraryMember;
//        this.borrowDate = borrowDate;
//        this.returnDate = returnDate;
//        this.borrower = borrower;
//        this.book = book;
//    }

    public BorrowRecord() {
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id=" + id +
                ", libraryMember=" + libraryMember +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                '}';
    }
}
