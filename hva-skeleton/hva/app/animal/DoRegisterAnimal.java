package hva.app.animal;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.*;
import java.nio.channels.FileLockInterruptionException;
import java.text.Normalizer;
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
  private String _speciesName = null;
  private boolean _test;


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
    _test = _receiver.tryRegisterAnimal(_animalId, _animalName, _habitatId, _specieId);
} catch (InvalidArgException e) {
    throw new DuplicateEmployeeKeyException("Invalid argument.");
} catch (DuplicateKeyException e) {
    throw new DuplicateAnimalKeyException(_animalId);
} 

if (_test == false) {
  _speciesName = Form.requestString("Specie name: ");
  try {
      _receiver.registerSpecies(_specieId, _speciesName);
  } catch (InvalidArgException e) {
      throw new DuplicateEmployeeKeyException("Invalid argument.");
  }
}
_receiver.registerAnimal(_animalId, _animalName, _habitatId, _specieId);

  }
}

