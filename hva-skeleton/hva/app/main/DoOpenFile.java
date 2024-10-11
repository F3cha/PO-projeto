package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;

//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        //FIXME implement command is done?
        Form Ficheiro = new Form();

        Ficheiro.addStringField("NomeFicheiro", Prompt.openFile());
        Ficheiro.parse();
        String nomeFicheiro = Ficheiro.stringField("NomeFicheiro");
        try {

            _receiver.load(nomeFicheiro);
        } catch (UnavailableFileException efe) {
            throw new FileOpenFailedException(efe);
        }

    }
}
