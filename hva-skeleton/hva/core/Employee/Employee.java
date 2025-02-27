package hva.core.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public abstract class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String _idEmployee;
    private String _nameEmployee;
    private List<String> _responsibility;


    public Employee(String idEmployee, String nameEmployee) {
        _idEmployee = idEmployee;
        _nameEmployee = nameEmployee;
        _responsibility = new ArrayList<>();
    }

    public String getEmployeeId() {
        return _idEmployee;
    }
    

    public List<String> getResponsibility() {
        return _responsibility;
    }

    public void setResponsibility(List<String> responsibility) {
        _responsibility.addAll(responsibility);
    }

    public String getEmployeeName() {
        return _nameEmployee;
    }


    public void addResponsibility(String responsibility) {
        _responsibility.add(responsibility);
    }
    
    public void removeResponsibility(String responsibility) {
        _responsibility.remove(responsibility);
    }

    public boolean hasResponsibility(String responsibility) {
        if (_responsibility.contains(responsibility.toLowerCase())) {
            return true;
        }
        else if (_responsibility.contains(responsibility.toUpperCase())) {
            return true;
        }
        else {
            return false;
        }
        }

    }