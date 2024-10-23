package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Habitat.Habitat;
import java.text.Normalizer;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  private String _habitatId;

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    _habitatId = Form.requestString("Insira o id do habitat: ");

    
  }
}
