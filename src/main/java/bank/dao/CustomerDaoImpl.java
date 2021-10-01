package bank.dao;

import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class CustomerDaoImpl implements CustomerDao {
    private final SessionFactory sessionFactory;

    @Override
    public Customer findById(Long id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
        session.close();
        return customers;
    }
}
