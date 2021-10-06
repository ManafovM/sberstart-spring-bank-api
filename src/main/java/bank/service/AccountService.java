package bank.service;

import bank.entity.Account;

import java.util.List;

public interface AccountService extends GenericService<Account> {
    Account getByCustomerIdById(long customerId, long id);

    List<Account> getAllByCustomerId(long id);
}
