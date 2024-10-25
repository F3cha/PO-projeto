package hva.core.Species;

import java.io.Serializable;

/**
 * Represents a species with an ID and a name.
 * Implements Serializable for object serialization.
 */
public class Species implements Serializable {
    private static final long serialVersionUID = 1L;

    private String _idSpecies;
    private String _nameSpecies;

    /**
     * Constructs a new Species with the specified ID and name.
     *
     * @param idSpecies The unique identifier for the species.
     * @param nameSpecies The name of the species.
     */
    public Species(String idSpecies, String nameSpecies) {
        _idSpecies = idSpecies;
        _nameSpecies = nameSpecies;
    }

    /**
     * Gets the unique identifier of the species.
     *
     * @return The species ID.
     */
    public String getSpeciesId() {
        return _idSpecies;
    }

    /**
     * Gets the name of the species.
     *
     * @return The species name.
     */
    public String getSpeciesName() {
        return _nameSpecies;
    }
}