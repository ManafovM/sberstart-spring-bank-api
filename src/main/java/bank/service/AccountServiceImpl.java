package bank.service;

import bank.dao.GenericDao;
import bank.dto.AccountDto;
import bank.dto.converter.AccountConverter;
import bank.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements GenericService<AccountDto> {
    private GenericDao<Account> dao;
    private AccountConverter converter;

    @Autowired
    public void setDao(GenericDao<Account> dao) {
        this.dao = dao;
        this.dao.setClazz(Account.class);
    }

    public AccountDto getById(long id) {
        Account account = dao.get(id).orElseThrow(RuntimeException::new);
        account.getCards().iterator();
        return converter.toDto(account);
    }

    public List<AccountDto> getAll() {
        List<Account> accounts = dao.getAll();
        accounts.forEach(account -> account.getCards().iterator());
        return accounts.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public void create(AccountDto accountDto) {
        dao.save(converter.toEntity(accountDto));
    }

    public AccountDto update(AccountDto accountDto) {
        return converter.toDto(dao.update(converter.toEntity(accountDto)));
    }

    public void delete(AccountDto accountDto) {
        dao.delete(converter.toEntity(accountDto));
    }
}
