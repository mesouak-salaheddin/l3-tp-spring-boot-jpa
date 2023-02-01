package fr.uga.l3miage.data.repo;

import java.util.List;

/**
 * A base repository contract for CRUD operations on entities.
 * @param <T> the entity type
 * @param <I> the id type
 */
public interface Repository<T, I> {

    /**
     * Save or update an entity.
     * @param entity the entity to save
     */
    void save(T entity);

    /**
     * Delete an entity.
     * @param entity  entity to remove
     */
    void delete(T entity);

    /**
     * Retrieve an entity by id.
     * @param id id of the entity to remove
     * @return the entity of null
     */
    T findById(I id);

    /**
     * Retrieve all entities of a given type.
     * @return all entities of a given type or an empty collection
     */
    List<T> getAll();

}
