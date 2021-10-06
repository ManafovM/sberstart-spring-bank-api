package bank.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountDto {
    private long id;
    private String number;
    private BigDecimal amount;
    private List<CardDto> cards;
    private long customerId;
}
