package hva.core;

public class Veterinary extends Employee {
    public Veterinary(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
    }

    public int satisfaction() {
        //
        return 1;
    }
}
