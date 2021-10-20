package bank.controller;


import bank.controller.assembler.CustomerModelAssembler;
import bank.dto.CustomerDto;
import bank.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {
    private final GenericService<CustomerDto> customerService;
    private final CustomerModelAssembler assembler;

    @Autowired
    public CustomerController(GenericService<CustomerDto> customerService, CustomerModelAssembler assembler) {
        this.customerService = customerService;
        this.assembler = assembler;
    }

    @GetMapping("/customers/{id}")
    public EntityModel<CustomerDto> getById(@PathVariable long id) {
        return assembler.toModel(customerService.getById(id));
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<CustomerDto>> getAll() {
        List<EntityModel<CustomerDto>> customers = customerService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).getAll()).withSelfRel());
    }

    @PostMapping("/customers")
    public CustomerDto create(@RequestBody CustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @PutMapping("/customers")
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        return customerService.update(customerDto);
    }

    @DeleteMapping("/customers")
    public void delete(@RequestBody CustomerDto customerDto) {
        customerService.delete(customerDto);
    }
}
