package bank.dao;

import bank.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class AccountDaoImpl extends AbstractDao<Account> implements GenericDao<Account> {
}
