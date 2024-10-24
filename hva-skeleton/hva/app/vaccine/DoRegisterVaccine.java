package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.*;
import hva.core.Species.Species;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import java.text.Normalizer;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.stream.Collectors;
import java.util.*;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  private String _vaccineId;
  private String _vaccineName;
  private List<String> _speciesKey;

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    _vaccineId = Form.requestString("Vacina id: ");
    _vaccineName = Form.requestString("Nome da vacina: ");
    String speciesInput = Form.requestString("Identificadores das espécies (separados por vírgula): ");

    String[] idsArray = speciesInput.split(",");

    //remover espaços em branco
    for (int i = 0; i < idsArray.length; i++) {
      idsArray[i] = idsArray[i].trim(); // Remove espaços em branco
    }

    try {
        _receiver.registerVaccine(_vaccineId, _vaccineName, idsArray);
    } catch (InvalidArgException e) {
      throw new DuplicateEmployeeKeyException("Invalid argument");
    } catch (DuplicateKeyException e) {
      throw new DuplicateVaccineKeyException("Duplicate vaccine key");
    } catch (UnknownKeyException e) {
      throw new UnknownSpeciesKeyException("Unknown Specie");
    }

  }
}
