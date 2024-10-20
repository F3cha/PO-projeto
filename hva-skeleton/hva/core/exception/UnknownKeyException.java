package hva.core.exception;

import java.io.Serial;

/**
 * Thrown when a method receives an argument that is not valid.
 */

public class UnknownKeyException extends Exception {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    /**
     * Constructs an InvalidArgumentException with a specific message.
     *
     * @param message the detail message
     */
    public UnknownKeyException(String message) {
        super(message);
    }

}