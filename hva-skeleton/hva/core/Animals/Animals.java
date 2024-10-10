package hva.core.Animals;

public class Animals {
    private String _idAnimal;
    private String _nameAnimal;
    private String _habitatAnimal;
    private String _specieAnimal;

    public Animals(String idAnimal, String nameAnimal, String habitatAnimal, String specieAnimal) {
        _idAnimal = idAnimal;
        _nameAnimal = nameAnimal;
        _habitatAnimal = habitatAnimal;
        _specieAnimal = specieAnimal;
    }

    public String getAnimalId() {
        return _idAnimal;
    }
}
