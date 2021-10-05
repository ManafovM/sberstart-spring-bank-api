package bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "[0-9]{2}/[0-9]{2}")
    private String expireDate;

    @NotNull
    @Pattern(regexp = "[0-9]{3}")
    private String cvc;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && number.equals(card.number) && expireDate.equals(card.expireDate) && cvc.equals(card.cvc) && status == card.status && account.equals(card.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expireDate, cvc, status, account);
    }
}
