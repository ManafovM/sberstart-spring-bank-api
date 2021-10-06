package bank.controller;


import bank.dto.CustomerDto;
import bank.entity.Customer;
import bank.service.GenericService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class CustomerController {
    private GenericService<Customer> customerService;
    private ModelMapper modelMapper;

    @GetMapping("/customers/{id}")
    public CustomerDto getById(@PathVariable Long id) {
        return convertToDto(customerService.getById(id));
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAll() {
        return customerService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/customers")
    public void create(@RequestBody CustomerDto customerDto) {
        customerService.create(convertToEntity(customerDto));
    }

    @PutMapping("/customers")
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        return convertToDto(customerService.update(convertToEntity(customerDto)));
    }

    @DeleteMapping("/customers")
    public void delete(@RequestBody CustomerDto customerDto) {
        customerService.delete(convertToEntity(customerDto));
    }

    private CustomerDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
