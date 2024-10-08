package hva.core;

import java.util.List;


public class Vaccine {
    private String _idVaccine;
    private String _nameVaccine;
    private List<String> _species;

    public Vaccine(String idVaccine, String nameVaccine, List<String> species) {
        _idVaccine = idVaccine;
        _nameVaccine = nameVaccine;
        _species = species;

    }

    public String getVaccineId() {
        return _idVaccine;
    }
}
