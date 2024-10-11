package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine.Vaccine;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Vaccine> vaccines = _receiver.getVaccines();

        _display.addAll(vaccines);
        _display.display();
  }
}
