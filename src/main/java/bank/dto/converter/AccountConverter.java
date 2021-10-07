package bank.dto.converter;

import bank.dto.AccountDto;
import bank.entity.Account;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountConverter {
    private ModelMapper mapper;

    public AccountDto toDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }

    public Account toEntity(AccountDto accountDto) {
        return mapper.map(accountDto, Account.class);
    }
}
