package hva.core.Employee;

import hva.core.Habitat.*;

import java.util.ArrayList;
import java.util.List;

public class Zookeeper extends Employee {
    private List<Habitat> habitats;

    public Zookeeper(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        this.habitats = new ArrayList<>();
    }

    public int satisfaction() {
        // acabar
        return 1;
    }

    public List<Habitat> getHabitats() {
        return habitats;
    }
}