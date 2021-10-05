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
public class CustomerServiceImpl implements CustomerService {
    private GenericDao<Customer> dao;

    @Autowired
    public void setDao(GenericDao<Customer> dao) {
        this.dao = dao;
        this.dao.setClazz(Customer.class);
    }

    public Customer getById(long id) {
        return dao.get(id).orElseThrow(RuntimeException::new);
    }

    public List<Customer> getAll() {
        return dao.getAll();
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
