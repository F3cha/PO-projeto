package hva.app.animal;

import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.CoreUnknownAnimalKeyException;
import hva.core.exception.CoreUnknownHabitatKey;
import hva.core.exception.InvalidArgException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {


  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("animalId", Prompt.animalKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = stringField("animalId");
    String habitatId = stringField("habitatId");

    try {
      _receiver.transferAnimal(animalId, habitatId);
    } catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(animalId);
    } catch (CoreUnknownHabitatKey e) {
      throw new UnknownHabitatKeyException(habitatId);
    }
  }
}
