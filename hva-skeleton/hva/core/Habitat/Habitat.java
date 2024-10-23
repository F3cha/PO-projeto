package hva.core.Habitat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import hva.core.Tree.*;

public class Habitat implements Serializable {
    private static final long serialVersionUID = 1L; //The tests werent working without this line
    private String _habitatId;
    private String _habitatName;
    private int _area;
    private List<String> _listaArvoresHabitat;


    public Habitat(String habitatId, String habitatName, int area) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _area = area;
        _listaArvoresHabitat = new ArrayList<String>();
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public String getHabitatName() {
        return _habitatName;
    }

    public int getArea() {
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

    
}
