package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.exception.DuplicateKeyException;
import hva.core.exception.InvalidArgException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

import java.security.InvalidParameterException;

//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("id", Prompt.employeeKey());
    addStringField("name", Prompt.employeeName());

  }
  
  @Override
  protected void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String type;
    while (true) {
      type = Form.requestString(Prompt.employeeType());
        if (type.equals("VET") || type.equals("TRT")) {
            break;
        }
    }
    try {
      _receiver.registerEmployee(id, name, type);
    } catch (DuplicateKeyException e) {
      throw new DuplicateEmployeeKeyException(id);
    } catch (InvalidArgException e) {
      throw new InvalidParameterException(e.getMessage());
    }
  }
}
