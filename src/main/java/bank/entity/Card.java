package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
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
    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
