package oosd.sait.springrest_libraryapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "membership_card")
public class MembershipCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String cardNumber;

    @Column(unique = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @Column(unique = false)
    @NotNull
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate expiryDate;

    @OneToOne(mappedBy = "membershipCard")
    private LibraryMember libraryMember;


    public MembershipCard() {
        this.cardNumber = generateCardNumber();

        LocalDate now = LocalDate.now();
        this.issueDate = now;
        this.expiryDate = now.plusYears(3);
    }

    /**
     * The method responsible for generating a new Card Number.
     * This method creates a random card number consisting of uppercase letters and digits.
     * The card number will be 12 characters long, formatted with a dash every 6 characters.
     * For example: "A1B2C3-D4E5F6".
     *
     * @return A String representing the generated card number, formatted as a 12-character string
     *         with a dash after the sixth character.
     */
    private String generateCardNumber() {
        int length = 12;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            if (i > 0 && i % 6 == 0) {
                cardNumber.append('-');
            }
            cardNumber.append(characters.charAt(random.nextInt(characters.length())));
        }
        return cardNumber.toString();

    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    @Override
    public String toString() {
        return "MembershipCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                ", libraryMember=" + libraryMember +
                '}';
    }
}
