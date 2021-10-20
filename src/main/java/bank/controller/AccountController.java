package bank.controller;

import bank.controller.assembler.AccountModelAssembler;
import bank.dto.AccountDto;
import bank.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AccountController {
    private final GenericService<AccountDto> accountService;
    private final AccountModelAssembler assembler;

    @Autowired
    public AccountController(GenericService<AccountDto> accountService, AccountModelAssembler assembler) {
        this.accountService = accountService;
        this.assembler = assembler;
    }

    @GetMapping("/accounts/{id}")
    public EntityModel<AccountDto> getById(@PathVariable long id) {
        return assembler.toModel(accountService.getById(id));
    }

    @GetMapping("/accounts")
    public CollectionModel<EntityModel<AccountDto>> getAll() {
        List<EntityModel<AccountDto>> accounts = accountService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).getAll()).withSelfRel());
    }

    @PostMapping("/accounts")
    public AccountDto create(@RequestBody AccountDto accountDto) {
        return accountService.create(accountDto);
    }

    @PutMapping("/accounts")
    public AccountDto update(@RequestBody AccountDto accountDto) {
        return accountService.update(accountDto);
    }

    @DeleteMapping("/accounts")
    public void delete(@RequestBody AccountDto accountDto) {
        accountService.delete(accountDto);
    }
}
