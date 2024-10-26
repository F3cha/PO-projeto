package hva.core;

import java.io.*;

import hva.core.Tree.Season;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import hva.core.exception.ImportFileException;
import hva.core.exception.UnrecognizedEntryException;
import hva.core.Hotel;


/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The current zoo hotel
     */
    private Hotel _hotel = new Hotel();
    private String _filename;

    public final Hotel getHotel() {
        return _hotel;
    }

    public Hotel newHotel() {
        return new Hotel();
    }

   /**
 * Shows the global satisfaction of the zoo hotel.
 *
 * @return the global satisfaction value
 */
public int showGlobalSatisfaction() {
    return _hotel.ShowAllSatisfaction();
}

    /**
 * Saves the serialized application's state into the associated file.
 *
 * @throws FileNotFoundException if the file cannot be created or opened
 * @throws MissingFileAssociationException if the current network does not have a file
 * @throws IOException if there is an error while serializing the state of the network to disk
 */
public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_filename == null || _filename.isEmpty()) {
        throw new MissingFileAssociationException();
    }
    try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(_filename))) {
        obOut.writeObject(_hotel);
        obOut.writeObject(_filename);
    }
}

    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException           if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException                     if there is some error while serializing the state of the network to disk.
     **/
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _filename = filename;
        save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *                 to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *                                  an error while processing this file.
     **/
    public void load(String filename) throws UnavailableFileException {
        try (ObjectInputStream obIn = new ObjectInputStream(new FileInputStream(filename))) {
            _hotel = (Hotel) obIn.readObject();
            _filename = (String) obIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Read text input file and initializes the current zoo hotel (which should be empty)
     * with the domain entitiesi representeed in the import file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException if some error happens during the processing of the
     *                             import file.
     **/
    public void importFile(String filename) throws ImportFileException {
        try {
            _hotel.importFile(filename);
        } catch (IOException | UnrecognizedEntryException e) {
            throw new ImportFileException(e.toString(), e);
        }
    }

    /**
     * Returns the zoo hotel managed by this instance.
     *
     * @return the current zoo hotel
     **/
public Season advanceHotelSeason() {
        return _hotel.advanceSeason();
    }
    public int seasonToNumber(Season season) {
        if (season == Season.Winter) {
            return 3;
        } else if (season == Season.Spring) {
            return 0;
        } else if (season == Season.Summer) {
            return 1;
        } else {
            return 2;
        }
    }
}
