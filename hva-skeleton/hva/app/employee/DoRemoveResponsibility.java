package hva.app.employee;

import hva.app.exception.NonExistantResponsibilityExceptiion;
import hva.core.Hotel;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.exception.InvalidArgException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        addStringField("employeeId", Prompt.employeeKey());
        addStringField("responsability", Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
        String employeeId = stringField("employeeId");
        String responsibility = stringField("responsability");
        try {
            _receiver.removeResponsibility(employeeId, responsibility);
        } catch (UnknownEmployeeKeyException e) {
            throw new CommandException("Employee ID does not exist.") {
            };
        } catch (InvalidArgException e) {
            throw new CommandException("Argument is invalid.") {
            };
        } catch (NonExistantResponsibilityExceptiion e) {
            throw new NoResponsibilityException(employeeId, responsibility);
        }
    }
}
