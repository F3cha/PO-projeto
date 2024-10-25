package hva.app.habitat;

import hva.app.exception.CoreUnknownSpeciesIdException;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.CoreUnknownHabitatKey;
import hva.core.exception.UnknownKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;



import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        addStringField("habitatId", Prompt.habitatKey());
        addStringField("specieID", hva.app.animal.Prompt.speciesKey());

    }

    @Override
    protected void execute() throws CommandException {
        String _habitatId = stringField("habitatId");
        String _specieId = stringField("specieID");
        String _influence;

        while (true) {
            _influence = Form.requestString(Prompt.habitatInfluence());
            if (_influence.equals("POS") || _influence.equals("NEG") || _influence.equals("NEU")) {
                break;
            }
        }
        try {
            _receiver.changeInfluenceSpecies(_habitatId, _specieId, _influence);

        } catch (CoreUnknownHabitatKey e) {
            throw new UnknownHabitatKeyException(_habitatId);
        } catch (CoreUnknownSpeciesIdException e) {
            throw new UnknownSpeciesKeyException(_specieId);
        }


    }
}
