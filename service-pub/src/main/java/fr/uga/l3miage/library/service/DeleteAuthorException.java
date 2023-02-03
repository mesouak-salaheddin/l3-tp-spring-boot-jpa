package fr.uga.l3miage.library.service;

/**
 * Thrown when an author cannot be deleted
 */
public class DeleteAuthorException extends Exception {

    public DeleteAuthorException(String message) {
        super(message);
    }

    public DeleteAuthorException(String message, Throwable cause) {
        super(message, cause);
    }

}
