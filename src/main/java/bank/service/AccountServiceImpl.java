package bank.service;

import bank.dao.AccountDao;
import bank.dao.GenericDao;
import bank.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountDao dao;

    @Autowired
    public void setDao(AccountDao dao) {
        this.dao = dao;
        this.dao.setClazz(Account.class);
    }

    public Account getById(long id) {
        Account account = dao.get(id).orElseThrow(RuntimeException::new);
        account.getCards().iterator();
        return account;
    }

    public Account getByCustomerIdById(long customerId, long id) {
        Account account = dao.getByCustomerIdById(customerId, id).orElseThrow(RuntimeException::new);
        account.getCards().iterator();
        return account;
    }

    public List<Account> getAll() {
        List<Account> accounts = dao.getAll();
        accounts.forEach(account -> account.getCards().iterator());
        return accounts;
    }

    public List<Account> getAllByCustomerId(long id) {
        List<Account> accounts = dao.getAllByCustomerId(id);
        accounts.forEach(account -> account.getCards().iterator());
        return accounts;
    }

    public void create(Account account) {
        dao.save(account);
    }

    public Account update(Account account) {
        return dao.update(account);
    }

    public void delete(Account account) {
        dao.delete(account);
    }
}
