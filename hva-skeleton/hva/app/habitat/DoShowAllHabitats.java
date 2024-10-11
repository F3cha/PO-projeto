package hva.app.habitat;

import hva.core.Habitat.Habitat;
import hva.core.Hotel;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    List<Habitat> habitats = _receiver.getHabitats();
        _display.addAll(habitats);
        _display.display();
  }
}
