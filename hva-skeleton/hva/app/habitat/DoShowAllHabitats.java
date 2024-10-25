package hva.app.habitat;

import hva.core.Habitat.Habitat;
import hva.core.Hotel;
import hva.core.Tree.Tree;

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
    protected void execute() { //FIXME: implement show Trees do habitat
        List<Habitat> habitats = _receiver.getHabitats();
        String habitatString = "";
        List<Habitat> sortedHabitat = new ArrayList<>(habitats);
        Collections.sort(sortedHabitat, Comparator.comparing(Habitat::getHabitatId));
        for (Habitat habitat : sortedHabitat) {


            habitatString = String.format("HABITAT|%s|%s|%d|%d",
                    habitat.getHabitatId(),
                    habitat.getHabitatName(),
                    habitat.getHabitatArea(), habitat.getNumberOfTrees())
            ;
            _display.addLine(habitatString);
            for (String treeId : habitat.getHabitatTreeList()) {
                Tree treeAtual = _receiver.getTreeById(treeId);
                String treeString = String.format("ÁRVORE|%s|%s|%d|%d|%s|%s",
                        treeAtual.getId(),
                        treeAtual.getName(),
                        treeAtual.getAge(),
                        treeAtual.getDifficulty(),
                        treeAtual.getType(),
                        treeAtual.getCicle());
                _display.addLine(treeString);


            }
        }
        _display.display();
    }
}