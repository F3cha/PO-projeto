package hva.app.vaccine;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.InvalidArgException;
import hva.core.exception.UnknownKeyException;
import hva.core.exception.CoreVaccineNotForVetException;
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
    addStringField("animalId", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String _vaccineId = stringField("vaccineId");
    String _vetId = stringField("vetId");
    String _animalId = stringField("animalId");
    try {
      _receiver.VaccinateAnimal(_animalId, _vetId, _vaccineId); //Se mexeres aqui e nao fizeres igual levas um carolo
    } catch (InvalidArgException e) {
      throw new UnknownVaccineKeyException(_vaccineId);
    } catch (UnknownKeyException e) {
      throw new UnknownVeterinarianKeyException(_vetId);
    } catch (CoreVaccineNotForVetException e) {
        Message.wrongVaccine(_vaccineId, _animalId);
    }

    

      //add dano
    }



  }

