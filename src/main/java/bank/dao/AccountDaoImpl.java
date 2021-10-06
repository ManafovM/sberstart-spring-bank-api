package bank.dao;

import bank.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {
    public Optional<Account> getByCustomerIdById(long customerId, long id) {
        return Optional.ofNullable(entityManager.createQuery("FROM Account WHERE id = " +
                id + " AND customer_id = " + customerId, Account.class).getSingleResult());
    }

    public List<Account> getAllByCustomerId(long id) {
        return entityManager.createQuery("FROM Account WHERE customer_id = " + id,
                Account.class).getResultList();
    }
}
