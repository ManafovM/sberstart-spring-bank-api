package bank.controller;


import bank.controller.assembler.CustomerModelAssembler;
import bank.dto.CustomerDto;
import bank.service.GenericService;
import lombok.AllArgsConstructor;
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
    private GenericService<CustomerDto> customerService;
    private CustomerModelAssembler assembler;

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
    public void create(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
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
