package bank.dao;

import bank.entity.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class CardDaoImpl extends AbstractDao<Card> implements GenericDao<Card> {
}
