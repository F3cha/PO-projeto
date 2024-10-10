package hva.core.Employee;

import java.util.List;

public abstract class Employee {
    private String _idEmployee;
    private String _nameEmployee;
    private List<String> _responsibility;

    public Employee(String idEmployee, String nameEmployee) {
        _idEmployee = idEmployee;
        _nameEmployee = nameEmployee;
    }

    public String getEmployeeId() {
        return _idEmployee;
    }

    public List<String> getResponsibility() {
        return _responsibility;
    }

    public void setResponsability(List<String> responsibility) {
        _responsibility.addAll(responsibility);
    }

    abstract int satisfaction();
}
