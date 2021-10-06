package bank.controller;

import bank.dto.CardDto;
import bank.entity.Card;
import bank.service.GenericService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class CardController {
    private GenericService<Card> cardService;
    private ModelMapper modelMapper;

    @GetMapping("/cards/{id}")
    public CardDto getById(@PathVariable Long id) {
        return convertToDto(cardService.getById(id));
    }

    @GetMapping("/cards")
    public List<CardDto> getAll() {
        return cardService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
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
