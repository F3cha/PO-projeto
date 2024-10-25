package hva.app.employee;

import hva.core.Employee.Employee;
import hva.core.Employee.Veterinary;
import hva.core.Employee.Zookeeper;
import hva.core.Hotel;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all employees registered in this zoo hotel.
 */
class DoShowAllEmployees extends Command<Hotel> {

    DoShowAllEmployees(Hotel receiver) {
        super(Label.SHOW_ALL_EMPLOYEES, receiver);
    }

    @Override
    protected void execute() {
        String employeeString = "";
        List<Employee> employees = _receiver.getEmployees();

        for (Employee emp : employees) {

            String speciesString = String.join(",",emp.getResponsibility());

            if (emp instanceof Veterinary) {
                if (!speciesString.isEmpty()) {
                    employeeString = String.format("VET|%s|%s|%s",
                            emp.getEmployeeId(),
                            emp.getEmployeeName(),
                            speciesString);
                } else {
                    employeeString = String.format("VET|%s|%s",
                            emp.getEmployeeId(),
                            emp.getEmployeeName());
                }
                _display.addLine(employeeString);
            } else if (emp instanceof Zookeeper) {

                if (!speciesString.isEmpty()) {
                    employeeString = String.format("TRT|%s|%s|%s",
                            emp.getEmployeeId(),
                            emp.getEmployeeName(),
                            speciesString);
                } else {
                    employeeString = String.format("TRT|%s|%s",
                            emp.getEmployeeId(),
                            emp.getEmployeeName());

                }
                _display.addLine(employeeString);
            }
            _display.display();
        }
    }
}