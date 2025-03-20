package oosd.sait.springrest_libraryapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    String title;

    @NotNull
    @Column(unique = true)
    String isbn;

    @NotNull
    int publicationYear;

    //Many to Many with Author


    //One to many with BorrowRecord
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecordList = new ArrayList<>();




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
