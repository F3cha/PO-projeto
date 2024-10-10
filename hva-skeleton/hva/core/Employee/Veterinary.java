package hva.core.Employee;

public class Veterinary extends Employee {
    public Veterinary(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
    }

    public int satisfaction() {
        //
        return 1;
    }
}
