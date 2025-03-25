package oosd.sait.springrest_libraryapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 1000)
    private String title;

    @NotNull
    @Column(unique = true)
    @Size(min = 10, max = 14)
    private String isbn;

    @NotNull
    private int publicationYear;

    //Many to Many with Author
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    //One to many with BorrowRecord
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecordList = new ArrayList<>();

//    public Book(String title, String isbn, int publicationYear, List<Author> authors, List<BorrowRecord> borrowRecordList) {
//        this.title = title;
//        this.isbn = isbn;
//        this.publicationYear = publicationYear;
//        this.authors = authors;
//        this.borrowRecordList = borrowRecordList;
//    }

    public Book() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<BorrowRecord> getBorrowRecordList() {
        return borrowRecordList;
    }

    public void setBorrowRecordList(List<BorrowRecord> borrowRecordList) {
        this.borrowRecordList = borrowRecordList;
    }

    // Method to check if the book is available for borrowing
    public boolean isAvailable() {
        // A book is available if there are no borrow records associated with it
        if(borrowRecordList == null || borrowRecordList.isEmpty()){
            return true;
        }
        return borrowRecordList.stream().noneMatch(record -> record.getReturnDate() == null);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", authors=" + authors +
                ", borrowRecordList=" + borrowRecordList +
                '}';
    }
}
