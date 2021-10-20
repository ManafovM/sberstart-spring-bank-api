package bank.dto.converter;

import bank.dto.CardDto;
import bank.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {
    private final ModelMapper mapper;

    public CardConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CardDto toDto(Card card) {
        return mapper.map(card, CardDto.class);
    }

    public Card toEntity(CardDto cardDto) {
        return mapper.map(cardDto, Card.class);
    }
}
