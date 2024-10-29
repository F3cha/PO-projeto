package hva.app.main;

import hva.core.HotelManager;
import hva.core.Tree.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
    DoAdvanceSeason(HotelManager receiver) {
        super(Label.ADVANCE_SEASON, receiver);
    }

    @Override
    protected final void execute() {
        int season = _receiver.seasonToNumber(_receiver.advanceHotelSeason());
        _display.addLine(season);
        _display.display();

    }
}
