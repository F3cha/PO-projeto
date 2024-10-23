package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Habitat.Habitat;
import hva.core.exception.InvalidArgException;

import hva.app.exception.UnknownHabitatKeyException;
import java.text.Normalizer;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  private String _habitatId;
  private int _newArea;

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    _habitatId = Form.requestString("Insira o id do habitat: ");
    _newArea = Form.requestInteger("Insira a nova área do habitat: ");

    try {
        _receiver.verifyHabitat(_habitatId);
    } catch (InvalidArgException e) {
      throw new UnknownHabitatKeyException(_habitatId);
    }

    for (Habitat hab: _receiver.getHabitats()) {
      if (hab.getHabitatId().equals(_habitatId)) {
          hab.changeArea(_newArea);
      } break;
    }
  }
}
