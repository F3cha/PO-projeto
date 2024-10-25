package hva.app.search;

import hva.core.Hotel;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.exception.UnknownKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("veterinaryId", "Identificador único do funcionário: ");
  }

  @Override
  protected void execute() throws CommandException {
    String veterinaryId = stringField("veterinaryId");

    try {
      _receiver.verifyVeterinary(veterinaryId);
    } catch (UnknownKeyException e) {
      throw new UnknownVeterinarianKeyException(veterinaryId);
    }

    for(int i = 0; i < _receiver.getVaccinationResgistration().size(); i += 3) {
      String _vaccineId = _receiver.getVaccinationResgistration().get(i);
      String _vetId = _receiver.getVaccinationResgistration().get(i+1);
      String _animalId = _receiver.getVaccinationResgistration().get(i+2);
      if(veterinaryId.equals(_vetId)) {
        String vaccines = String.format("REGISTO-VACINA|%s|%s|%s",
                _vaccineId,
                _vetId,
                _animalId);

        _display.addLine(vaccines);
      }
    }
    _display.display();
  }

}