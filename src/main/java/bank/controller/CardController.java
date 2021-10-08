package bank.controller;

import bank.controller.assembler.CardModelAssembler;
import bank.dto.BalanceDto;
import bank.dto.CardDto;
import bank.service.CardService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CardController {
    private final CardService cardService;
    private final CardModelAssembler assembler;

    public CardController(CardService cardService, CardModelAssembler assembler) {
        this.cardService = cardService;
        this.assembler = assembler;
    }

    @GetMapping("/cards/{id}")
    public EntityModel<CardDto> getById(@PathVariable long id) {
        return assembler.toModel(cardService.getById(id));
    }

    @GetMapping("/cards/{id}/balance")
    public BalanceDto getBalance(@PathVariable long id) {
        return cardService.getAccountByCardId(id).getAmount();
    }

    @PutMapping("/cards/{id}/balance")
    public BalanceDto deposit(@PathVariable long id, @RequestBody BalanceDto balanceDto) {
        return cardService.deposit(id, balanceDto);
    }

    @GetMapping("/cards")
    public CollectionModel<EntityModel<CardDto>> getAll() {
        List<EntityModel<CardDto>> cards = cardService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cards, linkTo(methodOn(CardController.class).getAll()).withSelfRel());
    }

    @PostMapping("/cards")
    public CardDto create(@RequestBody CardDto cardDto) {
        return cardService.create(cardDto);
    }

    @PutMapping("/cards")
    public CardDto update(@RequestBody CardDto cardDto) {
        return cardService.update(cardDto);
    }

    @DeleteMapping("/cards")
    public void delete(@RequestBody CardDto cardDto) {
        cardService.delete(cardDto);
    }
}
