package hva.core.exception;

import java.io.Serial;

public class SpeciesIdNonExistant extends Exception {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    public SpeciesIdNonExistant(String message) {
        super(message);
    }
}
