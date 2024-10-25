package hva.app.animal;

import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import hva.core.exception.CoreUnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("animalId", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      String animalId = stringField("animalId");
    _display.addLine(_receiver.getAnimalSatisfaction(animalId));
    _display.display();}
    catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(stringField("animalId"));
    }
  }
}
