package bank.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    Optional<T> get(long id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);
}
