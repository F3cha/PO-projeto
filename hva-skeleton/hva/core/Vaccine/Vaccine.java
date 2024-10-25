package hva.core.Vaccine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Vaccine implements Serializable {
    private String _idVaccine;
    private String _nameVaccine;
    private List<String> _species;
    private int _totalVaccines;
    private HashMap<String, String> _Vaccinelog;
    private HashMap<String, String> _DamageLog;

    public Vaccine(String idVaccine, String nameVaccine, List<String> species) {
        _idVaccine = idVaccine;
        _nameVaccine = nameVaccine;
        _species = species;
        _totalVaccines = 0;
        _Vaccinelog = new HashMap<>();
        _DamageLog = new HashMap<>();


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

    /*
    public void addVaccineLog(String animalId, String date) {
        _Vaccinelog.put(animalId, date);
        _totalVaccines++;
    }
    */

    public void addVaccineAplication() {
        _totalVaccines++;
    }

    public int getVaccinesTotal(){
        return _totalVaccines;
    }

    public boolean hasbeenVaccinated(String animalId) {
        if (_Vaccinelog.containsKey(animalId.toLowerCase())) {
            return true;
        } else if (_Vaccinelog.containsKey(animalId.toUpperCase())) {
            return true;
        }
        return false;
    }
    public void addDamageLog(String animalId,String damage){
        _DamageLog.put(animalId,damage);
    }
    public boolean vaccineContainsSpecies(String species){
        if(_species.contains(species.toLowerCase())){
            return true;
        }
        else if(_species.contains(species.toUpperCase())){
            return true;
        }
        return false;
    }


}

