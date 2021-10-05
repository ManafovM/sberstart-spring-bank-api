package bank.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String role;
    private List<AccountDto> accounts;
}
