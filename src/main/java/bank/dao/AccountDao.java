package bank.dao;

import bank.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends GenericDao<Account> {
    Optional<Account> getByCustomerIdById(long customerId, long id);

    List<Account> getAllByCustomerId(long id);
}
