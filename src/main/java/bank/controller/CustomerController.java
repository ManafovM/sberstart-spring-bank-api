package bank.controller;


import bank.entity.Customer;
import bank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/customers/{id}")
    public Customer getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping("/customers")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping("/customers")
    public void create(@RequestBody Customer customer) {
        customerService.create(customer);
    }

    @PutMapping("/customers")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/customers")
    public void delete(@RequestBody Customer customer) {
        customerService.delete(customer);
    }
}
