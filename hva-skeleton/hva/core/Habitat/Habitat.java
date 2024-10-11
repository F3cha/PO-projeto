package hva.core.Habitat;

import java.io.Serializable;

public class Habitat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String _habitatId;
    private String _habitatName;
    private int _area;


    public Habitat(String habitatId, String habitatName, int area) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _area = area;
    }

    public String getHabitatId() {
        return _habitatId;
    }
}
