package bank.dto.converter;

import bank.dto.CardDto;
import bank.entity.Card;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CardConverter {
    private ModelMapper mapper;

    public CardDto toDto(Card card) {
        return mapper.map(card, CardDto.class);
    }

    public Card toEntity(CardDto cardDto) {
        return mapper.map(cardDto, Card.class);
    }
}
