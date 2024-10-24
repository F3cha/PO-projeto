package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import hva.core.exception.InvalidArgException;
import hva.app.exception.AppInvalidArgException;
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
      addStringField("habitatId", Prompt.habitatKey());
      addStringField("treeId", Prompt.treeKey());
      addStringField("treeName", Prompt.treeName());
      addStringField("treeType", Prompt.treeType());
      addStringField("treeAge", Prompt.treeAge());
      addStringField("treeDfficulty", Prompt.treeDifficulty());
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

      String _habitatId = stringField("habitatId");
      String _treeId = stringField("treeId");
      String _treeName = stringField("treeName");
      String _treeType = stringField("treeType");
      int _treeAge = Integer.parseInt(stringField("treeAge"));
      int _treeDifficulty = Integer.parseInt(stringField("treeDfficulty"));

      if(_receiver.getHabitatById(_habitatId) != null && _receiver.getTreeById(_treeId) == null) {

        try {
            _receiver.createTree(_treeId, _treeName, _treeType, _treeAge, _treeDifficulty);
        } catch (InvalidArgException e) {
          throw new AppInvalidArgException("Argumento Inválido.");
        }
      }

  }



}

