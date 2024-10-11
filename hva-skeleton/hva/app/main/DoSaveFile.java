package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
import hva.core.exception.UnavailableFileException;
import java.io.FileNotFoundException;

// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    try {
      _receiver.save();
    } catch (MissingFileAssociationException e) {
      addStringField("Filename", Message.openFile());
      String filename = stringField("Filename");
      try {
        _receiver.saveAs(filename);
      } catch (FileNotFoundException e1) {
        // FIXME add behavior
      }
      catch (MissingFileAssociationException e2) {
        // FIXME add behavior
      } catch (IOException e3) {
        // FIXME add behavior
      }


      // FIXME add behavior
    } catch (IOException e) {
      // FIXME add behavior
    }

    // FIXME implement command and create a local Form
  }
}
