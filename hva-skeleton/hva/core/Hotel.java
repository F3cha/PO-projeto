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

    public List<Animals> getAnimals() {
        return animalList;
    }

    public boolean tryRegisterAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) throws InvalidArgException, DuplicateKeyException{
        // checks if the arguments are correct.

        boolean speciesExists = false;
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


        for (Species specie : speciesList) {
            if (specie.getSpeciesId().equals(speciesId)) {
                speciesExists = true;
                break;
            }
        }

        if (speciesExists == false) {
            return false;
        }

        return true;
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

    public void registerEmployee(String employeeId, String name, String empType) throws InvalidArgException, DuplicateKeyException{
        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidArgException("Employee's iD can't be null");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidArgException("Employee's name can't be null");
        }

        if (empType == null || empType.isEmpty()) {
            throw new InvalidArgException("Employee's habitat can't be null");
        }

        if (hasEmployee(employeeId)) {
            throw new DuplicateKeyException("Employee's ID already used");
        }

        if(empType.equals("VET")) {
            Veterinary newVeterinary = new Veterinary(employeeId, name);
            veterinaryList.add(newVeterinary);
            employeesList.add(newVeterinary);
        } else if (empType.equals("TRT")) {
            Zookeeper newZookeeper = new Zookeeper(employeeId, name);
            zookeeperList.add(newZookeeper);
            employeesList.add(newZookeeper);
        }
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
                List<String> responsibilityList = Arrays.asList(responsibility);
                emp.setResponsability(responsibilityList);
            }
        }

    }

    public List<Vaccine> getVaccines() {
        return vaccinesList;
    }

    public void registerVaccine(String vaccineId, String name, String[] speciesIds) throws InvalidArgException {
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

        for (Vaccine idVaccine : vaccinesList) {
            if (idVaccine.getVaccineId().equals(vaccineId)) {
                throw new InvalidArgException("The vaccine iD already exists");
            }
        }


        List<String> speciesIdsList = Arrays.asList(speciesIds);
        Vaccine newVaccine = new Vaccine(vaccineId, name, speciesIdsList);
        vaccinesList.add(newVaccine);
    }


    public void createTree(String TreeId, String name, String type, int age, int diff) throws InvalidArgException {

    }

    public List<Habitat> getHabitats() {
        return habitatsList;
    }

    public Habitat registerHabitat(String habitatId, String name, int area) throws InvalidArgException {
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
                throw new InvalidArgException("The habitat iD already exists");
            }
        }

        // create habitat

        Habitat newHabitat = new Habitat(habitatId, name, area);
        habitatsList.add(newHabitat);

        return newHabitat;
    }

    public void addTreeToHabitat(Habitat hab, String treeKey) {

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

