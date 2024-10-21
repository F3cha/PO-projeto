package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine.Vaccine;
import hva.core.Species.Species;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pt.tecnico.uilib.menus.Command;

//FIXME add more imports if needed


class DoShowAllVaccines extends Command<Hotel> {

    DoShowAllVaccines(Hotel receiver) {
        super(Label.SHOW_ALL_VACCINES, receiver);
    }

    @Override
    protected final void execute() {
        List<Vaccine> vaccines = _receiver.getVaccines();
        String vaccinesString = "";
        List<Vaccine> sortedVaccines = new ArrayList<>(vaccines);
        Collections.sort(sortedVaccines, Comparator.comparing(Vaccine::getVaccineId));
        

        for (Vaccine vac : sortedVaccines) {
            
            String speciesString = String.join(",", vac.getSpecies());

            if (speciesString.length() > 0) {
            vaccinesString = String.format("VACINA|%s|%s|0|%s",
                    vac.getVaccineId(),
                    vac.getVaccineName(),
                    speciesString);
            } else {
                vaccinesString = String.format("VACINA|%s|%s|0",
                vac.getVaccineId(),
                vac.getVaccineName(),
                speciesString);
            }
            _display.addLine(vaccinesString);

        }
        _display.display();

    }
}