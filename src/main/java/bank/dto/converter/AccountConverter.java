package bank.dto.converter;

import bank.dto.AccountDto;
import bank.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    private final ModelMapper mapper;

    public AccountConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AccountDto toDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }

    public Account toEntity(AccountDto accountDto) {
        return mapper.map(accountDto, Account.class);
    }
}
