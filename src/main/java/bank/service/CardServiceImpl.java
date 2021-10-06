package bank.service;

import bank.dao.GenericDao;
import bank.entity.Card;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CardServiceImpl implements GenericService<Card> {
    private GenericDao<Card> dao;

    @Autowired
    public void setDao(GenericDao<Card> dao) {
        this.dao = dao;
        this.dao.setClazz(Card.class);
    }

    public Card getById(long id) {
        return dao.get(id).orElseThrow(RuntimeException::new);
    }

    public List<Card> getAll() {
        return dao.getAll();
    }

    public void create(Card card) {
        dao.save(card);
    }

    public Card update(Card card) {
        return dao.update(card);
    }

    public void delete(Card card) {
        dao.delete(card);
    }
}
