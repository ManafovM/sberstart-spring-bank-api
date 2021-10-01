package bank.dao;

import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CustomerDao implements CrudDao<Customer> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Customer> get(long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    @Override
    public List<Customer> getAll() {
        Query query = entityManager.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
    }

    @Override
    public void update(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(customer);
        transaction.commit();
    }

    @Override
    public void delete(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(customer);
        transaction.commit();
    }
}
