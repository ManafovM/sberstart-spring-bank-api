package bank.dao;

import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class CustomerDaoImpl extends AbstractDao<Customer> implements GenericDao<Customer> {
}
