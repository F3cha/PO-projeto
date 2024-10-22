package hva.core.Employee;

import hva.core.Habitat.*;

import java.util.ArrayList;
import java.util.List;

public class Zookeeper extends Employee {
    private List<String> _responsibility;

    public Zookeeper(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _responsibility = new ArrayList<String>();
    }

    public int satisfaction() {
        // acabar
        return 1;
    }


}