package bank.controller;

import bank.controller.assembler.AccountModelAssembler;
import bank.dto.AccountDto;
import bank.entity.Account;
import bank.service.GenericService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
public class AccountController {
    private GenericService<Account> accountService;
    private AccountModelAssembler assembler;
    private ModelMapper modelMapper;

    @GetMapping("/accounts/{id}")
    public EntityModel<AccountDto> getById(@PathVariable long id) {
        return assembler.toModel(convertToDto(accountService.getById(id)));
    }

    @GetMapping("/accounts")
    public CollectionModel<EntityModel<AccountDto>> getAll() {
        List<EntityModel<AccountDto>> accounts = accountService.getAll().stream()
                .map(this::convertToDto)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).getAll()).withSelfRel());
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
