package hva.app.employee;

import hva.core.Employee.Employee;
import hva.core.Employee.Veterinary;
import hva.core.Employee.Zookeeper;
import hva.core.Hotel;
import hva.core.Habitat.Habitat;
import hva.core.Species.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Collectors;

import pt.tecnico.uilib.menus.Command;

//FIXME add more imports if needed

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
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        Collections.sort(sortedEmployees, Comparator.comparing(Employee::getEmployeeId));
        for (Employee emp : sortedEmployees) {

        }
        if (emp instanceof Veterinary) {
            String speciesString = ((Veterinary) emp).getSpecies().stream()
                    .map(Species::toString)
                    .collect(Collectors.joining(","));
            if (speciesString.length() > 0) {
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
            String habitatString = ((Zookeeper) emp).getHabitats().stream()
                    .map(Habitat::toString)
                    .collect(Collectors.joining(","));
            if (habitatString.length() > 0) {
                employeeString = String.format("TRT|%s|%s|%s",
                        emp.getEmployeeId(),
                        emp.getEmployeeName(),
                        habitatString);
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