package hva.core;

import hva.core.Animals.*;
import hva.core.Employee.*;
import hva.core.Habitat.*;
import hva.core.Species.*;
import hva.core.Tree.*;
import hva.core.Vaccine.*;
import hva.core.exception.*;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

// FIXME import classes

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // FIXME define attributes

    private List<Species> speciesList;
    private List<Animals> animalList;
    private List<Employee> employeesList;
    private List<Veterinary> veterinaryList;
    private List<Zookeeper> zookeeperList;
    private List<Habitat> habitatsList;
    private List<Tree> treeList;
    private List<Vaccine> vaccinesList;
    private Season _currentSeason;

    // FIXME define contructor(s)

    public Hotel() {
        this.speciesList = new ArrayList<>();
        this.animalList = new ArrayList<>();
        this.employeesList = new ArrayList<>();
        this.veterinaryList = new ArrayList<>();
        this.zookeeperList = new ArrayList<>();
        this.habitatsList = new ArrayList<>();
        this.treeList = new ArrayList<>();
        this.vaccinesList = new ArrayList<>();

    }

    // FIXME define more methods
    public boolean hasAnimal(String animalId) {
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equals(animalId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEmployee(String employeeId) {
        for (Employee emp : employeesList) {
            if (emp.getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    public void transferAnimal(String animalId, String habitatId) throws InvalidArgException {
        //FIXME implemntar adicionar ao habitat
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equals(animalId)) {
                animal.setAnimalHabitat(habitatId);
                break;
            }
        }
    }

    public boolean hasSpecies(String speciesId) {
        boolean speciesExists = false;
        for (Species specie : speciesList) {
            if (specie.getSpeciesId().equals(speciesId)) {
                speciesExists = true;
                break;
            }
        }

        if (speciesExists == false) {
            return false;
        }

        return false;
    }

    public void verifyHabitat(String habitatId) throws InvalidArgException {
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equals(habitatId)) {
                break;
            }
            throw new InvalidArgException(habitatId + "doesnt exist");
        }
    }

    public Species getSpeciesById(String idSpecies, List<Species> allSpecies) {
        for (Species species : allSpecies) {
            if (species.getSpeciesId().equals(idSpecies)) {
                return species; // Retorna o objeto Species correspondente ao ID
            }
        }
        return null; // Retorna null se a espécie não for encontrada
    }

    public List<Animals> getAnimals() {
        return animalList;
    }

    public boolean tryRegisterAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) throws InvalidArgException, DuplicateKeyException {
        // checks if the arguments are correct.


        if (animalId == null || animalId.isEmpty()) {
            throw new InvalidArgException("Animal's iD can't be null");
        }

        if (nameAnimals == null || nameAnimals.isEmpty()) {
            throw new InvalidArgException("Animal's name can't be null");
        }

        if (habitatId == null || habitatId.isEmpty()) {
            throw new InvalidArgException("Animal's habitat can't be null");
        }

        if (speciesId == null || speciesId.isEmpty()) {
            throw new InvalidArgException("Animal's species iD can't be null");
        }

        // checks if the iD of the new animal already exists.

        if (hasAnimal(animalId)) {
            throw new DuplicateKeyException("Animal's ID already used");
        }


        if (hasSpecies(speciesId)) {
            return true;
        }

        return false;
    }

    // create animal
    public void registerAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) {
        Animals newAnimal = new Animals(animalId, nameAnimals, habitatId, speciesId);
        animalList.add(newAnimal);
    }

    public void registerSpecies(String speciesId, String name) throws InvalidArgException {
        // checks if the arguments are correct.

        if (speciesId == null || speciesId.isEmpty()) {
            throw new InvalidArgException("The species iD is not valid");
        }

        // create species

        Species newEspecies = new Species(speciesId, name);
        speciesList.add(newEspecies);

    }

    public List<Employee> getEmployees() {
        //if (empType.equals("VET")) {
        //    return veterinaryList;
        //} else if (empType.equals("TRT")) {
        //    return zookeeperList;
        //}
        return employeesList;
    }

    public void registerEmployee(String employeeId, String name, String empType) throws InvalidArgException, DuplicateKeyException {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidArgException("Employee's iD can't be null");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidArgException("Employee's name can't be null");
        }

        if (empType == null || empType.isEmpty() ) {
            throw new InvalidArgException("Employee's type cant be that one");
        }

        if (hasEmployee(employeeId)) {
            throw new DuplicateKeyException("Employee's ID already used");
        }

        if (empType.equals("VET")) {
            Veterinary newVeterinary = new Veterinary(employeeId, name);
            veterinaryList.add(newVeterinary);
            employeesList.add(newVeterinary);
        } else if (empType.equals("TRT")) {
            Zookeeper newZookeeper = new Zookeeper(employeeId, name);
            zookeeperList.add(newZookeeper);
            employeesList.add(newZookeeper);
        }
    }

    public boolean verifyEmployeeType(String empType) {
        if (empType.equals("VET")) {
            return true;
        } else if (empType.equals("TRT")) {
            return true;
        }
        return false;
    }

    public void addResponsibility(String employeeId, String responsibility) throws InvalidArgException {
        // checks if the arguments are correct.

        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidArgException("The employee iD is not valid");
        }

        if (responsibility == null || responsibility.isEmpty()) {
            throw new InvalidArgException("Employee without any responsibility to add");
        }

        for (Employee emp : employeesList) {
            if (emp.getEmployeeId().equals(employeeId)) {
                emp.addResponsability(responsibility);
            }
        }

    }

    public List<Vaccine> getVaccines() {
        return vaccinesList;
    }

    public void registerVaccine(String vaccineId, String name, String[] speciesIds) throws InvalidArgException, DuplicateKeyException, UnknownKeyException {
        // checks if the arguments are correct.

        if (vaccineId == null || vaccineId.isEmpty()) {
            throw new InvalidArgException("The vaccine iD is not valid");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidArgException("Vaccine's name is not valid");
        }

        if (speciesIds == null || vaccineId.length() == 0) {
            throw new InvalidArgException("Vaccine without any species");
        }

        // checks if vaccine iD already exists

        List<String> speciesIdList = new ArrayList<>();
        for (String speciesId : speciesIds) {
            Species species = getSpeciesById(speciesId, speciesList); // Assumindo que este método existe e retorna um objeto Species
            if (species == null) {
                throw new UnknownKeyException("Specie Id doesn't exist: " + speciesId);
            }
            speciesIdList.add(species.getSpeciesId());
        }


        Vaccine newVaccine = new Vaccine(vaccineId, name, speciesIdList);
        vaccinesList.add(newVaccine);
    }


    public void createTree(String TreeId, String name, String type, int age, int diff) throws InvalidArgException {
        // checks if the arguments are correct.

        if (TreeId == null || TreeId.isEmpty()) {
            throw new InvalidArgException("The tree iD is not valid");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidArgException("Tree's name is not valid");
        }

        if (type == null || type.isEmpty()) {
            throw new InvalidArgException("Tree's type is not valid");
        }

        if (age <= 0 ) {
            throw new InvalidArgException("Tree's age is not valid");
        }

        if (diff <= 0) {
            throw new InvalidArgException("Tree's difficulty is not valid");
        }

        // checks if tree iD already exists



        // create tree

        //Tree newTree = new Tree(TreeId, name, type, age, diff);
        //treeList.add(newTree);
    }

    public List<Habitat> getHabitats() {
        return habitatsList;
    }

    public Habitat registerHabitat(String habitatId, String name, int area) throws InvalidArgException, DuplicateKeyException {
        // checks if the arguments are correct.
        if (habitatId == null || habitatId.isEmpty()) {
            throw new InvalidArgException("The employee iD is not valid");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidArgException("Employee's name is not valid");
        }

        if (area <= 0) {
            throw new InvalidArgException("Employee's type is not valid");
        }

        // checks if habitat iD already exists

        for (Habitat auxHabitatId : habitatsList) {
            if (auxHabitatId.getHabitatId().equals(habitatId)) {
                throw new DuplicateKeyException("The habitat iD already exists");
            }
        }

        // create habitat

        Habitat newHabitat = new Habitat(habitatId, name, area);
        habitatsList.add(newHabitat);

        return newHabitat;
    }

    public void addTreeToHabitat(Habitat hab, String treeKey) {

    }

    public String returnIdbyNameSpecies(String speciesName) throws SpeciesIdNonExistant {
        for (Species specie : speciesList) {
            if (specie.getSpeciesName().equals(speciesName)) {
                return specie.getSpeciesId();
            }
        }
        throw new SpeciesIdNonExistant("Species not found");
    }


    /**
     * Read text input file and create corresponding domain entities.
     *
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException                if there is an IO erro while processing the text file
     **/
    void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
        Parser parser = new Parser(this);
        parser.parseFile(filename);
    }
}

