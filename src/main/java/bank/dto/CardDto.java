package bank.dto;

import bank.entity.Status;
import lombok.Data;

@Data
public class CardDto {
    private long id;
    private String number;
    private String expireDate;
    private Status status;
}
