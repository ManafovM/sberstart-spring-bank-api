package bank.service;

import bank.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getById(long id);

    List<Customer> getAll();

    void create(Customer customer);

    Customer update(Customer customer);

    void delete(Customer customer);
}
