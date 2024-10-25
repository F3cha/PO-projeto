package hva.core.Habitat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hva.core.Animals.Animals;
import hva.core.Tree.*;

public class Habitat implements Serializable {
    private static final long serialVersionUID = 1L; //The tests werent working without this line
    private String _habitatId;
    private String _habitatName;
    private int _area;
    private List<String> _listaArvoresHabitat;
    private HashMap<String,String> _MapInfluenceSpecies;
    private List<Animals> _listaAnimaisHabitat;


    public Habitat(String habitatId, String habitatName, int area) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _area = area;
        _listaArvoresHabitat = new ArrayList<>();
        _MapInfluenceSpecies = new HashMap<>();
        _listaAnimaisHabitat = new ArrayList<>();
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public String getHabitatName() {
        return _habitatName;
    }

    public int getHabitatArea() {
        return _area;
    }

    public List<String> getHabitatTreeList(){
        return _listaArvoresHabitat;
    }

    public void setArea(int newArea) {
        _area = newArea;
    }

    public int getNumberOfTrees() {
        return _listaArvoresHabitat.size();
    }

    public void addTreeToHabitat(String treeId) {
        _listaArvoresHabitat.add(treeId);
    }

    public void changeInfluenceSpecies(String speciesId, String influence) {
        if (_MapInfluenceSpecies.containsKey(speciesId.toLowerCase())) {
            _MapInfluenceSpecies.replace(speciesId, influence);
        } else if (_MapInfluenceSpecies.containsKey(speciesId.toUpperCase())) {
            _MapInfluenceSpecies.replace(speciesId, influence);
        } else {
        _MapInfluenceSpecies.put(speciesId, influence);
    }}

    public String getInfluenceSpecies(String speciesId) {
        return _MapInfluenceSpecies.get(speciesId);
    }

    public void addAnimalToHabitat(Animals animal) {
        _listaAnimaisHabitat.add(animal);
        _MapInfluenceSpecies.put(animal.getAnimalSpecie(), "NEU");
    }

    public List<Animals> getListAnimalsInHabitat() {
        return _listaAnimaisHabitat;
    }
}
