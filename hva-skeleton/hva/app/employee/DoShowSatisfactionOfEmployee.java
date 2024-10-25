package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.exception.UnknownKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("employeeId", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME falta Satisfacao dos veterinarios
    String employeeId = stringField("employeeId");
    try {
      int satisfaction = _receiver.getSatisfactionOfEmployee(employeeId);
      _display.addLine(satisfaction);
      _display.display();
    } catch (UnknownKeyException e) {
      throw new CommandException("Employee ID does not exist.") {
      };
    }
  }
}
