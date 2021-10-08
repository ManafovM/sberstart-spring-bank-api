package bank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceDto {
    private BigDecimal amount;

    public BalanceDto(BigDecimal amount) {
        this.amount = amount;
    }
}
