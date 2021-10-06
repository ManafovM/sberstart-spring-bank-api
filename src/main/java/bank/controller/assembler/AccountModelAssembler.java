package bank.controller.assembler;

import bank.controller.AccountController;
import bank.dto.AccountDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<AccountDto, EntityModel<AccountDto>> {
    @Override
    public EntityModel<AccountDto> toModel(AccountDto accountDto) {
        return EntityModel.of(accountDto,
                WebMvcLinkBuilder.linkTo(methodOn(AccountController.class).getById(accountDto.getId())).withSelfRel(),
                linkTo(methodOn(AccountController.class).getAll()).withRel("accounts"));
    }
}
