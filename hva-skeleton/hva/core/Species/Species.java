package hva.core.Species;

import java.io.Serializable;


public class Species implements Serializable {
    private static final long serialVersionUID = 1L;
    private String _idSpecies;
    private String _nameSpecies;


    public Species(String idSpecies, String nameSpecies) {
        _idSpecies = idSpecies;
        _nameSpecies = nameSpecies;
    }

    public String getSpeciesId() {
        return _idSpecies;
    }

    public String getSpeciesName() {
        return _nameSpecies;
    }


}
