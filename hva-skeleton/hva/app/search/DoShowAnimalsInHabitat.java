package hva.app.search;

import hva.core.Animals.Animals;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.CoreUnknownHabitatKey;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected void execute() throws CommandException {
        String _habitatId = stringField("habitatId");
        List<Animals> animals;
        String animalString;
        try {
            animals = _receiver.returnAnimaisInHabitat(_habitatId);
            Collections.sort(animals, Comparator.comparing(Animals::getAnimalId));
        } catch (CoreUnknownHabitatKey e) {
            throw new UnknownHabitatKeyException(_habitatId);
        }
        for (Animals animal : animals) {
            animalString = String.format("ANIMAL|%s|%s|%s|%s|%s",
                    animal.getAnimalId(),
                    animal.getAnimalName(),
                    animal.getAnimalSpecie(),
                    animal.getAnimalState(),
                    animal.getAnimalHabitat());
            _display.addLine(animalString);
        }
        _display.display();
    }
}
