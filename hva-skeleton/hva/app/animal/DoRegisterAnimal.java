package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.InvalidArgException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  private String _animalId;
  private String _animalName;
  private String _specieId;
  private String _habitatId;


  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    //FIXME add command fields
  }
  
  @Override

  protected final void execute() throws CommandException {
    _animalId = Form.requestString("Animal ID: ");
    _animalName = Form.requestString("Animal Name: ");
    _specieId = Form.requestString("Species: ");
    _habitatId = Form.requestString("Habitat ID: ");

try {
      _receiver.registerAnimal(_animalId, _animalName, _habitatId, _specieId);
    } catch (InvalidArgException e) {
      throw new DuplicateAnimalKeyException("Invalid argument.");
    }
  
}
}
