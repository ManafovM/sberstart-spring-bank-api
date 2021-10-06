package bank.service;

import java.util.List;

public interface GenericService<T> {
    T getById(long id);

    List<T> getAll();

    void create(T entity);

    T update(T entity);

    void delete(T entity);
}