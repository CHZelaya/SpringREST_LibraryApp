package oosd.sait.springrest_libraryapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "library_member")
public class LibraryMember {

    //Properties/Db Columns
    @Id //Primary Key
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date membershipDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="membershipCard_id", referencedColumnName = "id")
    private MembershipCard membershipCard;

    @OneToMany(mappedBy = "library_member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    public LibraryMember(String name, String email, Date membershipDate, MembershipCard membershipCard, List<BorrowRecord> borrowRecords) {
        this.name = name;
        this.email = email;
        this.membershipDate = membershipDate;
        this.membershipCard = membershipCard;
        this.borrowRecords = borrowRecords;
    }

    public LibraryMember() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", membershipDate=" + membershipDate +
                '}';
    }
}
