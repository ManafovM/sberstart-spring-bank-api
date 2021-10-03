package bank.service;

import bank.dao.GenericDao;
import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    GenericDao<Customer> customerDao;

    @Override
    public Customer getById(long id) {
        return customerDao.get(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public void create(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }
}
