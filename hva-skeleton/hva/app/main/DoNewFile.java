package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.exception.UnavailableFileException;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;


import java.io.FileNotFoundException;
import java.io.IOException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
    DoNewFile(HotelManager receiver) {
        super(Label.NEW_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        _display.addLine(" Novo hotel criado.");
        _display.display(); // continua sem deixar criar varios hoteis
        _receiver.newHotel();


        //FIXME implement command
    }
}
