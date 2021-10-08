package bank.dao;

import bank.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends AbstractDao<Customer> implements GenericDao<Customer> {
}
