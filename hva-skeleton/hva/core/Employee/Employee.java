package hva.core;

import java.util.List;

abstract class Employee {
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
