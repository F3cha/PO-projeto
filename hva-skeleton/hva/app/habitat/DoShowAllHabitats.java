package hva.app.habitat;

import hva.core.Habitat.Habitat;
import hva.core.Hotel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

//FIXME add more imports if needed



//Show all habitats of this zoo hotel.

class DoShowAllHabitats extends Command<Hotel> {

    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
    }

    @Override
    protected void execute() {
        List<Habitat> habitats = _receiver.getHabitats();
        String habitatString = "";
        List<Habitat> sortedHabitat = new ArrayList<>(habitats);
        Collections.sort(sortedHabitat, Comparator.comparing(Habitat::getHabitatId));
        for (Habitat habitat: sortedHabitat) {


            habitatString = String.format("HABITAT|%s|%s|%d|0",
                    habitat.getHabitatId(),
                    habitat.getHabitatName(),
                    habitat.getArea());
                    _display.addLine(habitatString);
        }
        _display.display();
    }
}