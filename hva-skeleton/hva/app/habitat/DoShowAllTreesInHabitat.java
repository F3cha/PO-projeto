package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Habitat.Habitat;
import hva.core.Tree.Tree;
import java.text.Normalizer;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.core.exception.*;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  private String _habitatId;
  private Habitat _auxhabitat;
  private Tree _auxTree;

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    _habitatId = Form.requestString(Prompt.habitatKey());


    try {
      _receiver.verifyHabitat(_habitatId);
    } catch (InvalidArgException e) {
      throw new UnknownHabitatKeyException(_habitatId);
    }

    for (Habitat hab: _receiver.getHabitats()) {
      if (hab.getHabitatId().equals(_habitatId)) {
        _auxhabitat = hab;
      }
    }


    for (Tree tree : _receiver.getTrees()) {
      if (_auxhabitat.getHabitatTreeList().contains(tree.getId())) {
        String treeString = String.format("ÁRVORE|%s|%s|%d|%d|%s|%s",
                tree.getId(),
                tree.getName(),
                tree.getAge(),
                tree.getDifficulty(),
                tree.getClass(),
                tree.getCicle());
        _display.addLine(treeString);
      }
    }
    _display.display();
  }

}
