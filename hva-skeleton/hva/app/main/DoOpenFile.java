package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("Filename", Message.openFile());
  }

  @Override
  protected final void execute() throws CommandException {

      try {
      //FIXME implement command is done?
        String filename = stringField("Filename");
        _receiver.load(filename);
      } catch (UnavailableFileException efe) {
      throw new FileOpenFailedException(efe);
      }

  }
}
