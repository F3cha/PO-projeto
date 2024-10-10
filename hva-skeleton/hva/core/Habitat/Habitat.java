package hva.core.Habitat;

public class Habitat {
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
