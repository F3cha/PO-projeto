package hva.core;

public class Animals {
    private String _idAnimal;
    private String _nameAnimal;

    public Animals(String idAnimal, String nameAnimal) {
        _idAnimal = idAnimal;
        _nameAnimal = nameAnimal;
    }

    public String getAnimalId() {
        return _idAnimal;
    }
}
