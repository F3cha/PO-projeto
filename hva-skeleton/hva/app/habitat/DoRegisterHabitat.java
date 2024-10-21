package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.InvalidArgException;
import hva.core.exception.DuplicateKeyException;
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
    _habitatId = Form.requestString("Insira o id do habitat: ");
    _habitatName = Form.requestString("Insira o nome do habitat: ");
    _habitatArea = Form.requestInteger("Insira a área do habitat: ");
    
    try {
        _receiver.registerHabitat(_habitatId, _habitatName, _habitatArea);
    } catch (InvalidArgException e) {
      throw new DuplicateEmployeeKeyException("erro");
    } catch (DuplicateKeyException e) {
      throw new DuplicateHabitatKeyException(_habitatId);
    }
  }
}
