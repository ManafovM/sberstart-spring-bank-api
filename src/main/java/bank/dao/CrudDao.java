package bank.dao;

import java.util.List;

public interface CrudDao<T> {
    T findById(Long id);

    void save(T entity);

    List<T> findAll();
}
