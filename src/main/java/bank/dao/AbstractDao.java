package bank.dao;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    @Setter
    private Class<T> clazz;

    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    public List<T> getAll() {
        return entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            entityManager.remove(entityManager.merge(entity));
        }
    }
}
