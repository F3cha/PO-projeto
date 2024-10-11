package hva.app.employee;

import hva.core.Employee.Employee;
import hva.core.Hotel;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {

    List<Employee> employee = _receiver.getEmployees();

    _display.addAll(employee);
    _display.display();
  }
}
