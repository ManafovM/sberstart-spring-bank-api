package bank.dao;

import bank.entity.Card;
import org.springframework.stereotype.Repository;

@Repository
public class CardDaoImpl extends AbstractDao<Card> implements GenericDao<Card> {
}
