package bank.dto;

import bank.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private long id;
    private String number;
    private String expireDate;
    private Status status;
    private long accountId;
}
