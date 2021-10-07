package bank.controller.assembler;

import bank.controller.CardController;
import bank.dto.CardDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardModelAssembler implements RepresentationModelAssembler<CardDto, EntityModel<CardDto>> {
    @Override
    public EntityModel<CardDto> toModel(CardDto cardDto) {
        return EntityModel.of(cardDto,
                linkTo(methodOn(CardController.class).getById(cardDto.getId())).withSelfRel(),
                linkTo(methodOn(CardController.class).getBalance(cardDto.getId())).withRel("balance"),
                linkTo(methodOn(CardController.class).getAll()).withRel("cards"));
    }
}
