package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    for(int i = 0; i < _receiver.getVaccinationResgistration.size(); i += 3) {
      String _vaccineId = _receiver.getVaccinationResgistration.get(i);
      String _vetId = _receiver.getVaccinationResgistration.get(i+1);
      String _animalId = _receiver.getVaccinationResgistration.get(i+2);

      String vaccines = String.format("REGISTO-VACINA|%s|%s|%s", 
        _vaccineId,
        _vetId,
        _animalId);
      }
    } 
  }

