package hva.app.animal;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.*;

import java.nio.channels.FileLockInterruptionException;
import java.text.Normalizer;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {


    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("animalId", Prompt.animalKey());
        addStringField("animalName", Prompt.animalName());
        addStringField("specieId", Prompt.speciesKey());
        addStringField("habitatId", "Identificador único do habitat: ");
    }

    @Override

    protected final void execute() throws CommandException {
        String _animalId = stringField("animalId");
        String _animalName = stringField("animalName");
        String _specieId = stringField("specieId");
        String _habitatId = stringField("habitatId");
        if (_receiver.hasSpecies(_specieId)) {

            try {
                _receiver.tryRegisterAnimal(_animalId, _animalName, _habitatId, _specieId);
            } catch (InvalidArgException e) {
                throw new DuplicateAnimalKeyException("Invalid argument.");
            } catch (DuplicateKeyException e) {
                throw new DuplicateAnimalKeyException(_animalId);
            }
        }

        String _speciesName = Form.requestString(Prompt.speciesName());
        try {
            _specieId = _receiver.returnIdbyNameSpecies(_speciesName);
            _receiver.tryRegisterAnimal(_animalId, _animalName, _habitatId, _specieId);
        } catch (InvalidArgException e) {
            //FIXME: handle exception
        } catch (DuplicateKeyException e) {
            throw new DuplicateAnimalKeyException(_animalId);
        } catch (SpeciesIdNonExistant e) {
            throw new CommandException("Species ID does not exist.") {
            };
        }
    }
}


