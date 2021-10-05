package bank.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void setClazz(Class<T> clazz);

    Optional<T> get(long id);

    List<T> getAll();

    void save(T entity);

    T update(T entity);

    void delete(T entity);
}
