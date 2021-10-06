package bank.controller;

import bank.dto.AccountDto;
import bank.entity.Account;
import bank.service.GenericService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class AccountController {
    private GenericService<Account> accountService;
    private ModelMapper modelMapper;

    @GetMapping("/accounts/{id}")
    public AccountDto getById(@PathVariable Long id) {
        return convertToDto(accountService.getById(id));
    }

    @GetMapping("/accounts")
    public List<AccountDto> getAll() {
        return accountService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/accounts")
    public void create(@RequestBody AccountDto accountDto) {
        accountService.create(convertToEntity(accountDto));
    }

    @PutMapping("/accounts")
    public AccountDto update(@RequestBody AccountDto accountDto) {
        return convertToDto(accountService.update(convertToEntity(accountDto)));
    }

    @DeleteMapping("/accounts")
    public void delete(@RequestBody AccountDto accountDto) {
        accountService.delete(convertToEntity(accountDto));
    }

    private AccountDto convertToDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    private Account convertToEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }
}
