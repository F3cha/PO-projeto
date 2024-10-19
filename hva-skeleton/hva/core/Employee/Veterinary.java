package hva.core.Employee;

import hva.core.Species.*;
import java.util.ArrayList;
import java.util.List;

public class Veterinary extends Employee {
    private List<Species> species;

    public Veterinary(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        this.species = new ArrayList<>();
    }

    public int satisfaction() {
        // acabar
        return 1;
    }

    public List<Species> getSpecies() {
        return species;
    }
}