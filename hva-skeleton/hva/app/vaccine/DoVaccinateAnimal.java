package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownKeyException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineId", Prompt.vaccineKey());
    addStringField("vetId", Prompt.veterinarianKey());
    addStringField("animalId", Prompt.animalId());
  }

  @Override
  protected final void execute() throws CommandException {
    String _vaccineId = stringField("vaccineId");
    String _vetId = stringField("vetId");
    String _animalId = stringField("animalId");


    try {
        _receiver.verifyVet(_vetId);
        _receiver.verifyVaccine(_vaccineId);
        _receiver.verifyAnimal(_animalId);

        _receiver.getSpeciesUsingAnimalId(_animalId);
        _receiver.getResponsibilityEmp(_vetId);

    } catch (UnknownKeyException e) {
      throw new UnknownVeterinarianKeyException(_vetId);
    }
  }
}
