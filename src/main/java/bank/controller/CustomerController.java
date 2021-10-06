package bank.controller;


import bank.controller.assembler.CustomerModelAssembler;
import bank.dto.CustomerDto;
import bank.entity.Customer;
import bank.service.GenericService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
public class CustomerController {
    private GenericService<Customer> customerService;
    private CustomerModelAssembler assembler;
    private ModelMapper modelMapper;

    @GetMapping("/customers/{id}")
    public EntityModel<CustomerDto> getById(@PathVariable Long id) {
        return assembler.toModel(convertToDto(customerService.getById(id)));
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<CustomerDto>> getAll() {
        List<EntityModel<CustomerDto>> customers = customerService.getAll().stream()
                .map(this::convertToDto)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).getAll()).withSelfRel());
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
