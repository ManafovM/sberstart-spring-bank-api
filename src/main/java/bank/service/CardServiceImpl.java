package bank.service;

import bank.dao.GenericDao;
import bank.dto.AccountDto;
import bank.dto.BalanceDto;
import bank.dto.CardDto;
import bank.dto.converter.AccountConverter;
import bank.dto.converter.CardConverter;
import bank.entity.Account;
import bank.entity.Card;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    private final GenericDao<Card> dao;
    private final CardConverter cardConverter;
    private final AccountConverter accountConverter;

    public CardServiceImpl(GenericDao<Card> dao, CardConverter cardConverter, AccountConverter accountConverter) {
        this.dao = dao;
        this.dao.setClazz(Card.class);
        this.cardConverter = cardConverter;
        this.accountConverter = accountConverter;
    }

    public CardDto getById(long id) {
        return cardConverter.toDto(dao.get(id).orElseThrow(RuntimeException::new));
    }

    public List<CardDto> getAll() {
        return dao.getAll().stream()
                .map(cardConverter::toDto)
                .collect(Collectors.toList());
    }

    public CardDto create(CardDto cardDto) {
        return cardConverter.toDto(dao.save(cardConverter.toEntity(cardDto)));
    }

    public CardDto update(CardDto cardDto) {
        return cardConverter.toDto(dao.update(cardConverter.toEntity(cardDto)));
    }

    public void delete(CardDto cardDto) {
        dao.delete(cardConverter.toEntity(cardDto));
    }

    public AccountDto getAccountByCardId(long id) {
        return accountConverter.toDto(dao.get(id).orElseThrow(RuntimeException::new).getAccount());
    }

    public BalanceDto deposit(long id, BalanceDto balanceDto) {
        Card card = dao.get(id).orElseThrow(RuntimeException::new);
        Account account = card.getAccount();
        account.setAmount(account.getAmount().add(balanceDto.getAmount()));
        return accountConverter.toDto(account).getAmount();
    }
}
