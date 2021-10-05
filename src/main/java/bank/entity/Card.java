package bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Pattern(regexp = "[0-9]{16}")
    private String number;

    @NotNull
    private Date expireDate;

    @NotNull
    private int cvc;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    private enum Status {
        NEW,
        ACTIVE,
        BLOCKED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && cvc == card.cvc && number.equals(card.number) && expireDate.equals(card.expireDate) && status == card.status && account.equals(card.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expireDate, cvc, status, account);
    }
}
