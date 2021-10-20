package bank.service;

import java.util.List;

public interface GenericService<T> {
    /**
     * Получает сущность типа T по id
     *
     * @param id идентификатор сущности
     * @return сущность
     */
    T getById(long id);

    /**
     * Получает все сущности типа T
     *
     * @return список сущностей
     */
    List<T> getAll();

    /**
     * Создает сущность типа T
     *
     * @param entity новая сущность
     * @return созданная сущность
     */
    T create(T entity);

    /**
     * Обновляет сущность типа T
     *
     * @param entity новая сущность
     * @return обновленная сущность
     */
    T update(T entity);

    /**
     * Удаляет сущность типа T
     *
     * @param entity удаляемая сущность
     */
    void delete(T entity);
}
