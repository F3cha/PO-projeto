package hva.app.habitat;

import hva.app.exception.AppInvalidArgException;
import hva.core.Hotel;
import hva.core.exception.*;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import java.text.Normalizer;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  private String _habitatId;
  private String _habitatName;
  private int _habitatArea;



  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    _habitatId = Form.requestString(Prompt.habitatKey());
    _habitatName = Form.requestString(Prompt.habitatName());
    _habitatArea = Form.requestInteger(Prompt.habitatArea());
    
    try {
        _receiver.registerHabitat(_habitatId, _habitatName, _habitatArea);
    } catch (InvalidArgException et) {
      throw new AppInvalidArgException(et.getMessage());
    } catch (DuplicateKeyException e) {
      throw new DuplicateHabitatKeyException(_habitatId);
    }
  }
}
