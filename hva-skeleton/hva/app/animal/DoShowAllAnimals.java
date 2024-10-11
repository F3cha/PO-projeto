package hva.app.animal;

import hva.core.Animals.Animals;
import hva.core.Hotel;
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

        // Display all the information collected
        _display.addAll(animals);
        _display.display();
    }
}
