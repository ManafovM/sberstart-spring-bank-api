package bank.service;

import bank.dao.GenericDao;
import bank.dto.CustomerDto;
import bank.dto.converter.CustomerConverter;
import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements GenericService<CustomerDto> {
    private GenericDao<Customer> dao;
    private CustomerConverter converter;

    @Autowired
    public void setDao(GenericDao<Customer> dao) {
        this.dao = dao;
        this.dao.setClazz(Customer.class);
    }

    public CustomerDto getById(long id) {
        Customer customer = dao.get(id).orElseThrow(RuntimeException::new);
        customer.getAccounts().forEach(account -> account.getCards().iterator());
        return converter.toDto(customer);
    }

    public List<CustomerDto> getAll() {
        List<Customer> customers = dao.getAll();
        customers.forEach(customer -> customer.getAccounts().forEach(account -> account.getCards().iterator()));
        return customers.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CustomerDto customerDto) {
        dao.save(converter.toEntity(customerDto));
    }

    public CustomerDto update(CustomerDto customerDto) {
        return converter.toDto(dao.update(converter.toEntity(customerDto)));
    }

    public void delete(CustomerDto customerDto) {
        dao.delete(converter.toEntity(customerDto));
    }
}
