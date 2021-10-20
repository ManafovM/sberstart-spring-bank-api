package bank.service;

import bank.dto.AccountDto;
import bank.dto.BalanceDto;
import bank.dto.CardDto;

public interface CardService extends GenericService<CardDto> {
    /**
     * Получает счет по id карты
     *
     * @param id идентификатор карты
     * @return счет карты
     */
    AccountDto getAccountByCardId(long id);


    /**
     * Вносит средства на счет карты
     *
     * @param id идентификатор карты
     * @param balanceDto сумма для внесения
     * @return баланс карты после внесения
     */
    BalanceDto deposit(long id, BalanceDto balanceDto);
}
