package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
  /*  //FIXME implement command ps so falta fazer o builder da arvore e adicionar
    String _idHabitat = Form.requestString(Prompt.habitatKey());
    String _idTree = Form.requestString(Prompt.treeKey());
    String _nameTree = Form.requestString(Prompt.treeName());
    int _age = Form.requestInteger(Prompt.treeAge());
    int _difficulty = Form.requestInteger(Prompt.treeDifficulty());
    while(true){
    String _type = Form.requestString(Prompt.treeType());
    if(_type.equals("CAD") || _type.equals("PER")){
      break;
    }
    }*/

  }



}

