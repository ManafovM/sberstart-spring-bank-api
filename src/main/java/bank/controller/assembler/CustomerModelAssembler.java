package bank.controller.assembler;

import bank.controller.CustomerController;
import bank.dto.CustomerDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerDto, EntityModel<CustomerDto>> {
    @Override
    public EntityModel<CustomerDto> toModel(CustomerDto customerDto) {
        return EntityModel.of(customerDto,
                WebMvcLinkBuilder.linkTo(methodOn(CustomerController.class).getById(customerDto.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getAll()).withRel("customers"));
    }
}
