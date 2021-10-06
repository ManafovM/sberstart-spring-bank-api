package bank.service;

import bank.dao.GenericDao;
import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements GenericService<Customer> {
    private GenericDao<Customer> dao;

    @Autowired
    public void setDao(GenericDao<Customer> dao) {
        this.dao = dao;
        this.dao.setClazz(Customer.class);
    }

    public Customer getById(long id) {
        Customer customer = dao.get(id).orElseThrow(RuntimeException::new);
        customer.getAccounts().forEach(account -> account.getCards().iterator());
        return customer;
    }

    public List<Customer> getAll() {
        List<Customer> customers = dao.getAll();
        customers.forEach(customer -> customer.getAccounts().forEach(account -> account.getCards().iterator()));
        return customers;
    }

    public void create(Customer customer) {
        dao.save(customer);
    }

    public Customer update(Customer customer) {
        return dao.update(customer);
    }

    public void delete(Customer customer) {
        dao.delete(customer);
    }
}
