package hva.app.employee;

import hva.app.exception.NonExistantResponsibilityExceptiion;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownKeyException;
import hva.app.exception.NoResponsibilityException;
import hva.core.exception.InvalidArgException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("responsability", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = stringField("employeeId");
    String responsability = stringField("responsability");
    try {
      _receiver.addResponsibility(employeeId, responsability);
    } catch (UnknownKeyException e) {
      throw new UnknownEmployeeKeyException(employeeId) {
      };
    } catch (InvalidArgException e) {
      throw new CommandException("Argument is invalid.") {
      };
    } catch (NonExistantResponsibilityExceptiion e) {
      throw new NoResponsibilityException(employeeId, responsability);
    }
  }
}
