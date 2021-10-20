package bank.service;

import bank.dao.GenericDao;
import bank.dto.AccountDto;
import bank.dto.converter.AccountConverter;
import bank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements GenericService<AccountDto> {
    private final GenericDao<Account> dao;
    private final AccountConverter converter;

    @Autowired
    public AccountServiceImpl(GenericDao<Account> dao, AccountConverter converter) {
        this.dao = dao;
        this.dao.setClazz(Account.class);
        this.converter = converter;
    }

    @Transactional(readOnly = true)
    public AccountDto getById(long id) {
        Account account = dao.get(id).orElseThrow(RuntimeException::new);
        account.getCards().iterator();
        return converter.toDto(account);
    }

    @Transactional(readOnly = true)
    public List<AccountDto> getAll() {
        List<Account> accounts = dao.getAll();
        accounts.forEach(account -> account.getCards().iterator());
        return accounts.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountDto create(AccountDto accountDto) {
        return converter.toDto(dao.save(converter.toEntity(accountDto)));
    }

    @Transactional
    public AccountDto update(AccountDto accountDto) {
        return converter.toDto(dao.update(converter.toEntity(accountDto)));
    }

    @Transactional
    public void delete(AccountDto accountDto) {
        dao.delete(converter.toEntity(accountDto));
    }
}
