package hva.core;

public class Species {
    private String _idSpecies;
    private String _nameSpecies;

    public Species(String idSpecies, String nameSpecies) {
        _idSpecies = idSpecies;
        _nameSpecies = nameSpecies;
    }

    public String getSpeciesId() {
        return _idSpecies;
    }
    
}
