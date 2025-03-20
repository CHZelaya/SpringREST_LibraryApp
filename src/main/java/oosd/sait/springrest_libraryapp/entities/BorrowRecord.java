package oosd.sait.springrest_libraryapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="borrow_record")
public class BorrowRecord {
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "libraryMember_id")
    private LibraryMember libraryMember;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
