package hva.core.Employee;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public abstract class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public String getEmployeeName() {
        return _nameEmployee;
    }


public void addResponsability(String responsibility) {
    _responsibility.add(responsibility);
}
}