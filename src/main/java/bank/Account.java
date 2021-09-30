package bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;
    private BigDecimal amount;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
