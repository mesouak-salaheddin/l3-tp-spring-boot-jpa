package fr.uga.l3miage.library.service.base;

import fr.uga.l3miage.library.service.EntityNotFoundException;

import java.util.Collection;

/**
 * This interface abstract all create/read update/delete operation that can occur on a persistent object.
 *
 * @param <O> the type of persistence object
 * @param <I> type of the identifier
 */
public interface CRUDService<O, I> {


    /**
     * Saves a transient object
     *
     * @param object to be saved
     * @return the object with the identifier valued
     */
    O save(O object);

    /**
     * get a transient object
     *
     * @param id the object identifier
     * @return the object
     * @throws fr.uga.l3miage.library.service.EntityNotFoundException when the requested entity cannot be loaded
     */
    O get(I id) throws EntityNotFoundException;

    /**
     * Returns all objects
     *
     * @return all object as an {@link Collection}
     */
    Collection<O> list();

    /**
     * updates the object and return it (in case the object was updated internally)
     *
     * @param object the object to update
     * @return the updated object
     * @throws fr.uga.l3miage.library.service.EntityNotFoundException when the entity do not already exists
     */
    O update(O object) throws EntityNotFoundException;

    /**
     * Deletes an object
     *
     * @param id id of theobject to delete
     * @throws fr.uga.l3miage.library.service.EntityNotFoundException when the entity do not already exists
     */
    void delete(I id) throws EntityNotFoundException;

}
