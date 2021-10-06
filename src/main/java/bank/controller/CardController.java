package bank.controller;

import bank.controller.assembler.CardModelAssembler;
import bank.dto.CardDto;
import bank.entity.Card;
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
public class CardController {
    private GenericService<Card> cardService;
    private CardModelAssembler assembler;
    private ModelMapper modelMapper;

    @GetMapping("/cards/{id}")
    public EntityModel<CardDto> getById(@PathVariable Long id) {
        return assembler.toModel(convertToDto(cardService.getById(id)));
    }

    @GetMapping("/cards")
    public CollectionModel<EntityModel<CardDto>> getAll() {
        List<EntityModel<CardDto>> cards = cardService.getAll().stream()
                .map(this::convertToDto)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cards, linkTo(methodOn(CardController.class).getAll()).withSelfRel());
    }

    @PostMapping("/cards")
    public void create(@RequestBody CardDto cardDto) {
        cardService.create(convertToEntity(cardDto));
    }

    @PutMapping("/cards")
    public CardDto update(@RequestBody CardDto cardDto) {
        return convertToDto(cardService.update(convertToEntity(cardDto)));
    }

    @DeleteMapping("/cards")
    public void delete(@RequestBody CardDto cardDto) {
        cardService.delete(convertToEntity(cardDto));
    }

    private CardDto convertToDto(Card card) {
        return modelMapper.map(card, CardDto.class);
    }

    private Card convertToEntity(CardDto cardDto) {
        return modelMapper.map(cardDto, Card.class);
    }
}
