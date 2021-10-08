package bank.dao;

import bank.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends AbstractDao<Account> implements GenericDao<Account> {
}
