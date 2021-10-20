package bank.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    /**
     * Устанавливает значение поля clazz
     *
     * @param clazz класс
     */
    void setClazz(Class<T> clazz);

    /**
     * Получает сущность типа T по id
     *
     * @param id идентификатор сущности
     * @return сущность
     */
    Optional<T> get(long id);

    /**
     * Получает все сущности типа T
     *
     * @return список сущностей
     */
    List<T> getAll();

    /**
     * Сохраняет сущность типа T
     *
     * @param entity новая сущность
     * @return сохраненная сущность
     */
    T save(T entity);

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
