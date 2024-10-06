package hva.core;

abstract class Employee {
    private String _idEmployee;
    private String _nameEmployee;

    public Employee(String idEmployee, String nameEmployee) {
        _idEmployee = idEmployee;
        _nameEmployee = nameEmployee;
    }

    abstract int satisfaction();
}
