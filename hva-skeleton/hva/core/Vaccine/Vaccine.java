package hva.core.Vaccine;

import java.util.List;
import java.io.Serializable;

public class Vaccine implements Serializable {
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

public String getVaccineName() {
    return _nameVaccine;
}
public List<String> getSpecies() {
    return _species;
}
}

