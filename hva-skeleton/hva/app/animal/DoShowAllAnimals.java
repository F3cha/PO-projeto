package hva.app.animal;

import hva.core.Animals.*;
import hva.core.Hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);
    }

    @Override
    protected final void execute() {
        List<Animals> animals = _receiver.getAnimals();
        String animalString = "";


        List<Animals> sortedAnimals = new ArrayList<>(animals);
        Collections.sort(sortedAnimals, Comparator.comparing(Animals::getAnimalId));
        for (Animals animal : sortedAnimals) {
            animalString = String.format("ANIMAL|%s|%s|%s|VOID|%s",
                    animal.getAnimalId(),
                    animal.getAnimalName(),
                    animal.getAnimalSpecie(),
                    animal.getAnimalHabitat());
            _display.addLine(animalString);
        }
        _display.display();

    }
}