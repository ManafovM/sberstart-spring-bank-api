package bank.service;

import bank.dto.AccountDto;
import bank.dto.BalanceDto;
import bank.dto.CardDto;

public interface CardService extends GenericService<CardDto> {
    AccountDto getAccountByCardId(long id);

    BalanceDto deposit(long id, BalanceDto balanceDto);
}
