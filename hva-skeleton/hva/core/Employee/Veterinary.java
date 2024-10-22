package hva.core.Employee;

import hva.core.Species.*;
import java.util.ArrayList;
import java.util.List;

public class Veterinary extends Employee {
    List<String> _responsibility;

    public Veterinary(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _responsibility = new ArrayList<>();
    }

    public int satisfaction() {
        // acabar
        return 1;
    }


}