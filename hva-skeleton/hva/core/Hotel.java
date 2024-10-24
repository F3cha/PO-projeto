package hva.core;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.NonExistantResponsibilityExceptiion;
import hva.app.exception.UnknownEmployeeKeyException;

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
        speciesList = new ArrayList<>();
        animalList = new ArrayList<>();
        employeesList = new ArrayList<>();
        veterinaryList = new ArrayList<>();
        zookeeperList = new ArrayList<>();
        habitatsList = new ArrayList<>();
        treeList = new ArrayList<>();
        vaccinesList = new ArrayList<>();
        _currentSeason = Season.Spring;

    }

    /* Methods Related to Animals*/
    public String getSpeciesUsingAnimalId(String animalid) {
        for (Animals animal: animalList) {
            if (animal.getAnimalId().equals(animalid)) {
                return animal.getAnimalSpecie();
            }
        }
        return null;
    }

    public boolean hasAnimal(String animalId) {
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equals(animalId)) {
                return true;
            }
        }
        return false;
    }

    public void verifyAnimal(String animalId) throws UnknownKeyException {
        boolean found = false;
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equals(animal)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(animalId);
        }
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

    public boolean responsibilityIsSpecies(String responsibility) {
        for (Species species : speciesList) {
            if (species.getSpeciesId().equals(responsibility)) {
                return true;
            }
        }
        return false;
    }

    public boolean responsibilityIsHabitat(String responsibility) {
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equals(responsibility)) {
                return true;
            }
        }
        return false;
    }

    public void verifyHabitat(String habitatId) throws UnknownKeyException {
        boolean found = false;
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equals(habitatId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(habitatId + " doesn't exist");
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

    public Habitat getHabitatById(String habitatId) {
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equals(habitatId)) {
                return habitat;
            }
        }
        return null;
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

    public void verifyVet(String vetId) throws UnknownKeyException{
        boolean found = false;
        for (Veterinary aux: veterinaryList) {
            if(aux.getEmployeeId().equals(vetId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(vetId);
        }
    }

    public void isAuthorizedForVaccine(String vetId, String vacId) {

    }

    public void registerEmployee(String employeeId, String name, String empType) throws InvalidArgException, DuplicateKeyException {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidArgException("Employee's iD can't be null");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidArgException("Employee's name can't be null");
        }

        if (empType == null || empType.isEmpty()) {
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


    public void addResponsibility(String employeeId, String responsibility) throws InvalidArgException, UnknownEmployeeKeyException, NonExistantResponsibilityExceptiion {
        // checks if the arguments are correct.
        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidArgException("The employee iD is not valid");
        }

        if (responsibility == null || responsibility.isEmpty()) {
            throw new InvalidArgException("Employee without any responsibility to add");
        }

        Employee employee = getEmployeeById(employeeId);

        if (employee == null) {
            throw new UnknownEmployeeKeyException(employeeId);
        }
        if (!responsibilityIsHabitat(responsibility) && !responsibilityIsSpecies(responsibility)) {
            throw new NonExistantResponsibilityExceptiion("Responsibility doesn't exist");
        }
        if (employee instanceof Veterinary && responsibilityIsHabitat(responsibility)) {
            throw new InvalidArgException("Veterinary can't have habitat as responsibility");
        }
        if (employee instanceof Zookeeper && responsibilityIsSpecies(responsibility)) {
            throw new InvalidArgException("Zookeeper can't have species as responsibility");
        }
        if (!hasresponsibility(employee, responsibility)) {
            employee.addResponsibility(responsibility);
        }


    }

    public void removeResponsibility(String employeeId, String responsibility) throws InvalidArgException, UnknownEmployeeKeyException, NoResponsibilityException {
        // checks if the arguments are correct.

        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidArgException("The employee iD is not valid");
        }

        if (responsibility == null || responsibility.isEmpty()) {
            throw new InvalidArgException("Employee without any responsibility to add");
        }
        Employee employee = getEmployeeById(employeeId);
        if (employee == null) {
            throw new UnknownEmployeeKeyException(employeeId);
        }
        if (!responsibilityIsHabitat(responsibility) && !responsibilityIsSpecies(responsibility)) {
            throw new NoResponsibilityException(employeeId, responsibility);
        }
        if (employee instanceof Veterinary && responsibilityIsHabitat(responsibility)) {
            throw new InvalidArgException("Veterinary can't have habitat as responsibility");
        }
        if (employee instanceof Zookeeper && responsibilityIsSpecies(responsibility)) {
            throw new InvalidArgException("Zookeeper can't have species as responsibility");
        }
        if (hasresponsibility(employee, responsibility)) {
            employee.removeResponsibility(responsibility);
        } else {
            throw new NonExistantResponsibilityExceptiion("Responsibility doesn't exist");
        }

    }

    public Employee getEmployeeById(String idEmployee) {
        for (Employee emp : employeesList) {
            if (emp.getEmployeeId().equals(idEmployee)) {
                return emp;
            }
        }
        return null;
    }

    public boolean hasresponsibility(Employee employee, String responsibility) {
        List<String> responsibilities = employee.getResponsibility();
        for (String res : responsibilities) {
            if (res.equals(responsibility)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getResponsibilityEmp(String employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee.getResponsibility();
    }

    //Functions related to Vaccines
    public List<Vaccine> getVaccines() {
        return vaccinesList;
    }

    public void verifyVaccine(String vaccineId) throws UnknownKeyException{
        boolean found = false;
        for (Vaccine aux: vaccinesList) {
            if(aux.getVaccineId().equals(vaccineId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(vaccineId);
        }
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

        if (age <= 0) {
            throw new InvalidArgException("Tree's age is not valid");
        }

        if (diff <= 0) {
            throw new InvalidArgException("Tree's difficulty is not valid");
        }

        // checks if tree iD already exists


        // create tree

        if (type.equals("CADUCA")) {
            DecidiousTree newTree = new DecidiousTree(_currentSeason, age, diff, TreeId, name);
            treeList.add(newTree);
        } else {
            EvergreenTree newTree = new EvergreenTree(_currentSeason, age, diff, TreeId, name);
            treeList.add(newTree);
        }

    }

    public Tree getTreeById(String treeId) {
        for (Tree tree : treeList) {
            if (tree.getId().equals(treeId)) {
                return tree;
            }
        }
        return null;
    }

    public String getTreeCicle(String treeId) {
        Tree tree = getTreeById(treeId);
        return tree.getCicle();
    }

    public String verifyTree(String treeId) throws DuplicateKeyException {
        for (Tree tree : treeList) {
            if (tree.getId().equals(treeId)) {
                throw new DuplicateKeyException(treeId);
            }
        }
        return null;
    }

    public List<Tree> getTrees() {
        return treeList;
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

    public void addTreeToHabitat(String hab, String treeKey) {
        Habitat habitat = getHabitatById(hab);
        habitat.addTreeToHabitat(treeKey);

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

    public Season advanceSeason() {
        for (Tree tree : treeList) {
            tree.treeAdvanceSeason();
        }
        if (_currentSeason == Season.Spring) {
            _currentSeason = Season.Summer;
        } else if (_currentSeason == Season.Summer) {
            _currentSeason = Season.Autumn;
        } else if (_currentSeason == Season.Autumn) {
            _currentSeason = Season.Winter;
        } else {
            _currentSeason = Season.Spring;
        }
        return _currentSeason;


    }
}

