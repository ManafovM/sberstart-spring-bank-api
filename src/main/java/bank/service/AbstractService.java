package bank.service;

import bank.dao.GenericDao;

import java.util.List;

public abstract class AbstractService<T> {
    private GenericDao<T> genericDao;

    public T getById(long id) {
        return genericDao.get(id).orElseThrow(RuntimeException::new);
    }

    public List<T> getAll() {
        return genericDao.getAll();
    }

    public void create(T entity) {
        genericDao.save(entity);
    }

    public T update(T entity) {
        return genericDao.update(entity);
    }

    public void delete(T entity) {
        genericDao.delete(entity);
    }
}
