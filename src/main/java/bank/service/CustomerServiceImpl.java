package bank.service;

import bank.dao.GenericDao;
import bank.dto.CustomerDto;
import bank.dto.converter.CustomerConverter;
import bank.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements GenericService<CustomerDto> {
    private GenericDao<Customer> dao;
    private final CustomerConverter converter;

    @Autowired
    public CustomerServiceImpl(GenericDao<Customer> dao, CustomerConverter converter) {
        this.dao = dao;
        this.dao.setClazz(Customer.class);
        this.converter = converter;
    }

    @Transactional(readOnly = true)
    public CustomerDto getById(long id) {
        Customer customer = dao.get(id).orElseThrow(RuntimeException::new);
        customer.getAccounts().forEach(account -> account.getCards().iterator());
        return converter.toDto(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAll() {
        List<Customer> customers = dao.getAll();
        customers.forEach(customer -> customer.getAccounts().forEach(account -> account.getCards().iterator()));
        return customers.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerDto create(CustomerDto customerDto) {
        return converter.toDto(dao.save(converter.toEntity(customerDto)));
    }

    @Transactional
    public CustomerDto update(CustomerDto customerDto) {
        return converter.toDto(dao.update(converter.toEntity(customerDto)));
    }

    @Transactional
    public void delete(CustomerDto customerDto) {
        dao.delete(converter.toEntity(customerDto));
    }
}
