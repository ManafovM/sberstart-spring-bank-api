package bank.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Pattern(regexp = "[0-9]{16}")
    private String number;

    @NotBlank
    @Pattern(regexp = "[0-9]{2}/[0-9]{2}")
    private String expireDate;

    @NotBlank
    @Pattern(regexp = "[0-9]{3}")
    private String cvc;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.NEW;

    @NotBlank
    @ManyToOne()
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "account_id")
    private Account account;

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && number.equals(card.number) && expireDate.equals(card.expireDate) && cvc.equals(card.cvc) && status == card.status && account.equals(card.account) && Objects.equals(version, card.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expireDate, cvc, status, account, version);
    }
}
