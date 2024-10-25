package hva.core;

import hva.app.exception.CoreUnknownSpeciesIdException;
import hva.core.Animals.*;
import hva.core.Employee.*;
import hva.core.Habitat.*;
import hva.core.Species.*;
import hva.core.Tree.*;
import hva.core.Vaccine.*;
import hva.core.exception.*;

import java.io.*;
import java.util.*;


public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // FIXME define attributes

    private List<Species> _speciesList;
    private List<Animals> _animalsList;
    private List<Employee> _employeesList;
    private List<Veterinary> _veterinarysList;
    private List<Zookeeper> _zookeepersList;
    private List<Habitat> _habitatsList;
    private List<Tree> _treesList;
    private List<Vaccine> _vaccinesList;
    private List<String> _vaccinationRes;

    private Season _currentSeason;

    public Hotel() {
        _speciesList = new ArrayList<>();
        _animalsList = new ArrayList<>();
        _employeesList = new ArrayList<>();
        _veterinarysList = new ArrayList<>();
        _zookeepersList = new ArrayList<>();
        _habitatsList = new ArrayList<>();
        _treesList = new ArrayList<>();
        _vaccinesList = new ArrayList<>();
        _vaccinationRes = new ArrayList<>();
        _currentSeason = Season.Spring;


    }


    /**
     * Calculates the total satisfaction of all employees and animals in the hotel.
     *
     * @return The total satisfaction score.
     */
    public int ShowAllSatisfaction() {
        int totalSatisfaction = 0;

        // Iterate through the list of employees
        for (Employee emp : _employeesList) {
            try {
                // Add the satisfaction of each employee to the total
                totalSatisfaction += getSatisfactionOfEmployee(emp.getEmployeeId());
            } catch (UnknownKeyException e) {
                e.printStackTrace();
            }
        }

        // Iterate through the list of animals
        for (Animals animal : _animalsList) {
            String animalId = animal.getAnimalId();
            try {
                // Add the satisfaction of each animal to the total
                totalSatisfaction += getAnimalSatisfaction(animalId);
            } catch (CoreUnknownAnimalKeyException e) {
                e.printStackTrace();
            }
        }

        return totalSatisfaction;
    }

    /**
     * Retrieves the species ID of an animal using its animal ID.
     *
     * @param animalId The ID of the animal.
     * @return The species ID of the animal if found; `null` otherwise.
     */
    public String getSpeciesUsingAnimalId(String animalId) {
        // Iterate through the list of animals
        for (Animals animal : _animalsList) {
            // Check if the current animal ID matches the given ID (case-insensitive)
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return animal.getAnimalSpecie(); // Return the species ID of the animal
            }
        }
        return null; // Return null if the animal is not found
    }

    /**
     * Checks if an animal with the given ID exists in the animal list.
     *
     * @param animalId The ID of the animal to check.
     * @return `true` if an animal with the specified ID exists; `false` otherwise.
     */
    public boolean hasAnimal(String animalId) {
        // Iterate through the list of animals
        for (Animals animal : _animalsList) {
            // Check if the current animal ID matches the given ID (case-insensitive)
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return true; // Animal found
            }
        }
        return false; // Animal not found
    }

    /**
     * Transfers an animal to a new habitat, updating its habitat ID.
     *
     * @param animalId  The ID of the animal.
     * @param habitatId The ID of the habitat
     * @throws CoreUnknownAnimalKeyException if the animal ID is not found in the system.
     * @throws CoreUnknownHabitatKey         if the habitat ID is not found in the system.
     */

    public void transferAnimal(String animalId, String habitatId) throws CoreUnknownAnimalKeyException, CoreUnknownHabitatKey {
        if (!verifyAnimalExistence(animalId)) {
            throw new CoreUnknownAnimalKeyException(animalId);
        }
        if (!verifyHabitatExistence(habitatId)) {
            throw new CoreUnknownHabitatKey(habitatId);
        }
        Animals animal = getAnimalById(animalId);
        animal.setAnimalHabitat(habitatId);
        Habitat habitat = getHabitatById(habitatId);
        habitat.addAnimalToHabitat(animal);
    }

    /**
     * Verify if an habitat exists.
     *
     * @param habitatId The ID of the habitat.
     * @return `true` if a habitat with the specified ID exists; `false` otherwise.
     */

    public boolean verifyHabitatExistence(String habitatId) {
        for (Habitat habitat : _habitatsList) {
            if (habitat.getHabitatId().equalsIgnoreCase(habitatId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves an animal from the system by its ID.
     *
     * @param animalId The ID of the animal.
     * @return The `Animals` object with the specified ID if found; `null` otherwise.
     */

    public Animals getAnimalById(String animalId) {
        for (Animals animal : _animalsList) {
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return animal;
            }
        }
        return null;
    }

    /**
     * Checks if a species with the given ID exists in the species list.
     *
     * @param speciesId The ID of the species to check.
     * @return `true` if a species with the specified ID exists; `false` otherwise.
     */
    public boolean hasSpecies(String speciesId) {
        // Iterate through the list of species
        for (Species specie : _speciesList) {
            // Check if the current species ID matches the given ID (case-insensitive)
            if (specie.getSpeciesId().equalsIgnoreCase(speciesId)) {
                return true; // Species found
            }
        }
        return false; // Species not found
    }

    /**
     * Retrieves a species from the system by its ID.
     *
     * @param idSpecies  The ID of the species.
     * @param allSpecies A list of Species.
     * @return The `Species` object with the specified ID if found; `null` otherwise.
     */
    public Species getSpeciesById(String idSpecies, List<Species> allSpecies) {
        for (Species species : allSpecies) {
            if (species.getSpeciesId().equalsIgnoreCase(idSpecies)) {
                return species; // Return the Species object corresponding to the ID
            }
        }
        return null; // Return null if the species is not found
    }

    /**
     * Retrieves a species from the system by its name.
     *
     * @param speciesName The name of the species.
     * @return The ID of the species if found.
     * @throws UnknownKeyException if the species is not found.
     */
    public String returnIdbyNameSpecies(String speciesName) throws UnknownKeyException {
        for (Species specie : _speciesList) {
            if (specie.getSpeciesName().equalsIgnoreCase(speciesName)) {
                return specie.getSpeciesId();
            }
        }
        throw new UnknownKeyException("Species not found");
    }

    /**
     * Checks if an employee with the given ID exists in the employee list.
     *
     * @param employeeId The ID of the employee to check.
     * @return `true` if an employee with the specified ID exists; `false` otherwise.
     */
    public boolean hasEmployee(String employeeId) {
        for (Employee emp : _employeesList) {
            if (emp.getEmployeeId().equalsIgnoreCase(employeeId)) {
                return true; // Employee found
            }
        }
        return false; // Employee not found
    }

    /**
     * Verifies if an animal with the given ID exists in the animal list.
     *
     * @param animalId The ID of the animal to check.
     * @return `true` if an animal with the specified ID exists; `false` otherwise.
     */
    public boolean verifyAnimalExistence(String animalId) {
        // Iterate through the list of animals
        for (Animals animal : _animalsList) {
            // Check if the current animal ID matches the given ID (case-insensitive)
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return true; // Animal found
            }
        }
        return false; // Animal not found
    }

    public boolean responsibilityIsSpecies(String responsibility) {
        for (Species species : _speciesList) {
            if (species.getSpeciesId().equalsIgnoreCase(responsibility)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given responsibility corresponds to a habitat ID in the habitat list.
     *
     * @param responsibility The responsibility to check.
     * @return `true` if the responsibility matches a habitat ID; `false` otherwise.
     */
    public boolean responsibilityIsHabitat(String responsibility) {
        // Iterate through the list of habitats
        for (Habitat habitat : _habitatsList) {
            // Check if the current habitat ID matches the given responsibility (case-insensitive)
            if (habitat.getHabitatId().equalsIgnoreCase(responsibility)) {
                return true; // Responsibility matches a habitat ID
            }
        }
        return false; // Responsibility does not match any habitat ID
    }

    public void verifyHabitat(String habitatId) throws UnknownKeyException {
        //FIXME mendonca verificadores nao mandam erros, as funcoes que os utilizam e que mandam
        boolean found = false;
        for (Habitat habitat : _habitatsList) {
            if (habitat.getHabitatId().equalsIgnoreCase(habitatId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(habitatId + " doesn't exist");
        }
    }

    /**
     * Calculates the satisfaction of an animal based on its habitat and other factors.
     *
     * @param animalId The ID of the animal.
     * @return The satisfaction score of the animal.
     * @throws CoreUnknownAnimalKeyException if the animal ID is not found in the system.
     */
    public int getAnimalSatisfaction(String animalId) throws CoreUnknownAnimalKeyException {
        // Verify if the animal exists
        if (!verifyAnimalExistence(animalId)) {
            throw new CoreUnknownAnimalKeyException("Animal not found");
        }

        // Retrieve the animal and its habitat
        Animals animal = getAnimalById(animalId);
        String habitatId = animal.getAnimalHabitat();
        Habitat habitat = getHabitatById(habitatId);

        // Calculate the satisfaction score based on various factors
        return Math.round(20 + (3 * getEqualSpeciesInHabitat(habitatId, animalId))
                - (2 * (getTotalAnimalsInHabitat(habitatId) - getEqualSpeciesInHabitat(habitatId, animalId) - 1))
                + ((float) habitat.getHabitatArea() / getTotalAnimalsInHabitat(habitatId))
                + getInfluenceAnimalInHabiat(animalId, habitatId));
    }

    /**
     * Calculates the satisfaction of a zookeeper based on their responsibilities.
     *
     * @param zookeeperId The ID of the zookeeper.
     * @return The satisfaction score of the zookeeper.
     */
    public int getZookeeperSatisfaction(String zookeeperId) {
        // Retrieve the zookeeper
        Zookeeper zookeeper = (Zookeeper) getEmployeeById(zookeeperId);
        float totalSatisfaction = 0;

        // Calculate the total satisfaction based on the habitats they are responsible for
        for (String habitatId : zookeeper.getResponsibility()) {
            totalSatisfaction += (workInHabitat(habitatId) / getWorkersInHabitat(habitatId));
        }

        // Return the final satisfaction score
        return Math.round(300 - totalSatisfaction);
    }

    /**
     * Retrieves the satisfaction score of an employee based on their type.
     *
     * @param employeeId The ID of the employee.
     * @return The satisfaction score of the employee.
     * @throws UnknownKeyException if the employee ID is not found in the system.
     */
    public int getSatisfactionOfEmployee(String employeeId) throws UnknownKeyException {
        // Check if the employee exists
        if (!hasEmployee(employeeId)) {
            throw new UnknownKeyException("Employee not found");
        }

        // Retrieve the employee by their ID
        Employee employee = getEmployeeById(employeeId);

        // Determine the type of employee and calculate satisfaction accordingly
        if (employee instanceof Zookeeper) {
            return getZookeeperSatisfaction(employeeId);
        } else {
            return getVeterinarySatisfaction(employeeId);
        }
    }

    /**
     * Retrieves a list of animals that are in the specified habitat.
     *
     * @param habitatId The ID of the habitat.
     * @return A list of animals in the specified habitat.
     * @throws CoreUnknownHabitatKey if the habitat ID is not found in the system.
     */
    public List<Animals> returnAnimaisInHabitat(String habitatId) throws CoreUnknownHabitatKey {
        // Verify if the habitat exists
        if (!verifyHabitatExistence(habitatId)) {
            throw new CoreUnknownHabitatKey(habitatId);
        }

        List<Animals> animais = new ArrayList<>();
        // Iterate through the list of animals
        for (Animals animal : _animalsList) {
            // Check if the current animal's habitat ID matches the given habitat ID (case-insensitive)
            if (animal.getAnimalHabitat().equalsIgnoreCase(habitatId)) {
                animais.add(animal); // Add the animal to the list
            }
        }
        return animais; // Return the list of animals in the habitat
    }

    /**
     * Calculates the satisfaction of a veterinary based on the species they are responsible for.
     *
     * @param veterinaryId The ID of the veterinary.
     * @return The satisfaction score of the veterinary.
     */
    public int getVeterinarySatisfaction(String veterinaryId) {
        // Calculate the satisfaction score based on the species the veterinary is responsible for
        return Math.round(20 - VeterinarySpecies(veterinaryId));
    }

    /**
     * Calculates the total divisions of species populations by the number of veterinarians responsible for them.
     *
     * @param veterinaryId The ID of the veterinary.
     * @return The total divisions of species populations by the number of veterinarians.
     */
    public float VeterinarySpecies(String veterinaryId) {
        // Retrieve the veterinary by their ID
        Veterinary vet = (Veterinary) getEmployeeById(veterinaryId);
        float totalDivisions = 0;

        // Iterate through the list of species the veterinary is responsible for
        for (String speciesId : vet.getResponsibility()) {
            // Get the total population of the species
            int totalPopulation = getPopulationofSpecies(speciesId);
            // Get the number of veterinarians responsible for the species
            int numberOfVets = getVeterinarysResponsibleforSpecies(speciesId);
            // Add the division of total population by the number of veterinarians to the total
            totalDivisions += (float) totalPopulation / numberOfVets;
        }
        return totalDivisions;
    }

    /**
     * Retrieves the total population of a species based on its species ID.
     *
     * @param speciesId The ID of the species.
     * @return The total population of the species.
     */
    public int getPopulationofSpecies(String speciesId) {
        int total = 0;
        // Iterate through the list of animals
        for (Animals animal : _animalsList) {
            // Check if the current animal's species ID matches the given species ID (case-insensitive)
            if (animal.getAnimalSpecie().equalsIgnoreCase(speciesId)) {
                total++; // Increment the total population count
            }
        }
        return total; // Return the total population of the species
    }

    /**
     * Retrieves the number of veterinarians responsible for a given species.
     *
     * @param speciesId The ID of the species.
     * @return The number of veterinarians responsible for the species. Returns 1 if no veterinarians are found.
     */
    public int getVeterinarysResponsibleforSpecies(String speciesId) {
        int total = 0;
        // Iterate through the list of employees
        for (Employee emp : _employeesList) {
            // Check if the employee is a veterinary and has responsibility for the given species
            if (emp instanceof Veterinary && emp.hasResponsibility(speciesId)) {
                total++;
            }
        }
        // If no veterinarians are found, return 1
        return total == 0 ? 1 : total;
    }

    /**
     * Retrieves the number of zookeepers responsible for a given habitat.
     *
     * @param habitatId The ID of the habitat.
     * @return The number of zookeepers responsible for the habitat.
     */
    public int getWorkersInHabitat(String habitatId) {
        int totalWorkers = 0;
        // Iterate through the list of employees
        for (Employee emp : _employeesList) {
            // Check if the employee is a zookeeper and has responsibility for the given habitat
            if (emp instanceof Zookeeper && hasresponsibility(emp, habitatId)) {
                totalWorkers++; // Increment the total workers count
            }
        }
        return totalWorkers; // Return the total number of zookeepers responsible for the habitat
    }

    /**
     * Calculates the total work effort required in a habitat.
     *
     * @param habitatID The ID of the habitat.
     * @return The total work effort required in the habitat.
     */
    public int workInHabitat(String habitatID) {
        // Retrieve the habitat by its ID
        Habitat habitat = getHabitatById(habitatID);

        // Get the area of the habitat
        int area = habitat.getHabitatArea();

        // Get the population of animals in the habitat
        int population = habitat.getListAnimalsInHabitat().size();

        // Initialize the cleaning effort
        int cleaningEffort = 0;

        // Iterate through the list of tree IDs in the habitat
        for (String treeID : habitat.getHabitatTreeList()) {
            // Retrieve the tree by its ID
            Tree tree = getTreeById(treeID);
            // Add the cleaning effort of the tree to the total cleaning effort
            cleaningEffort += tree.getCleaningEffort();
        }

        // Calculate and return the total work effort
        return area + population * 3 + cleaningEffort;
    }


    /**
 * Retrieves the total number of animals in a given habitat.
 *
 * @param habitatId The ID of the habitat.
 * @return The total number of animals in the habitat.
 */
public int getTotalAnimalsInHabitat(String habitatId) {
    // Retrieve the habitat by its ID
    Habitat habitat = getHabitatById(habitatId);
    // Return the number of animals in the habitat
    return habitat.getListAnimalsInHabitat().size();
}

/**
 * Retrieves the influence score of an animal in a given habitat based on its species.
 *
 * @param animalId  The ID of the animal.
 * @param habitatId The ID of the habitat.
 * @return The influence score of the animal in the habitat.
 */
public int getInfluenceAnimalInHabiat(String animalId, String habitatId) {
    // Retrieve the species ID of the animal
    String speciesUsingAnimalId = getSpeciesUsingAnimalId(animalId);
    // Retrieve the influence of the species in the habitat
    String influence = getHabitatById(habitatId).getInfluenceSpecies(speciesUsingAnimalId);

    // Determine the influence score based on the influence type
    if (influence.equals("NEU")) {
        return 0;
    } else if (influence.equals("POS")) {
        return 20;
    } else {
        return -20;
    }
}

    /**
 * Retrieves the list of all animals in the hotel.
 *
 * @return A list of `Animals` objects.
 */
public List<Animals> getAnimals() {
    return _animalsList;
}

    /**
 * Attempts to register an animal by validating its details.
 *
 * @param animalId    The ID of the animal.
 * @param nameAnimals The name of the animal.
 * @param habitatId   The ID of the habitat.
 * @param speciesId   The ID of the species.
 * @return `true` if the species ID exists; `false` otherwise.
 * @throws InvalidArgException   if any of the arguments are invalid.
 * @throws DuplicateKeyException if the animal ID already exists.
 */
public boolean tryRegisterAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) throws InvalidArgException, DuplicateKeyException {
    // Check if the animal's name is valid
    if (nameAnimals == null || nameAnimals.isEmpty()) {
        throw new InvalidArgException("Animal's name can't be null");
    }

    // Check if the habitat ID is valid
    if (habitatId == null || habitatId.isEmpty()) {
        throw new InvalidArgException("Animal's habitat can't be null");
    }

    // Check if the species ID is valid
    if (speciesId == null || speciesId.isEmpty()) {
        throw new InvalidArgException("Animal's species ID can't be null");
    }

    // Check if the animal ID already exists
    if (hasAnimal(animalId)) {
        throw new DuplicateKeyException("Animal's ID already used");
    }

    // Check if the species ID exists
    return hasSpecies(speciesId);
}

    /**
 * Registers a new animal in the system and adds it to the specified habitat.
 *
 * @param animalId    The ID of the animal.
 * @param nameAnimals The name of the animal.
 * @param habitatId   The ID of the habitat.
 * @param speciesId   The ID of the species.
 */
public void registerAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) {
    // Create a new animal object
    Animals newAnimal = new Animals(animalId, nameAnimals, habitatId, speciesId);
    // Add the new animal to the animal list
    _animalsList.add(newAnimal);
    // Retrieve the habitat by its ID
    Habitat habitat = getHabitatById(habitatId);
    // Add the new animal to the habitat
    habitat.addAnimalToHabitat(newAnimal);
}

/**
 * Retrieves a habitat from the system by its ID.
 *
 * @param habitatId The ID of the habitat.
 * @return The `Habitat` object with the specified ID if found; `null` otherwise.
 */
public Habitat getHabitatById(String habitatId) {
    // Iterate through the list of habitats
    for (Habitat habitat : _habitatsList) {
        // Check if the current habitat ID matches the given ID (case-insensitive)
        if (habitat.getHabitatId().equalsIgnoreCase(habitatId)) {
            return habitat; // Return the habitat object
        }
    }
    return null; // Return null if the habitat is not found
}

/**
 * Registers a new species in the system.
 *
 * @param speciesId The ID of the species.
 * @param name      The name of the species.
 * @throws InvalidArgException if the species ID is invalid.
 */
public void registerSpecies(String speciesId, String name) throws InvalidArgException {
    // Check if the species ID is valid
    if (speciesId == null || speciesId.isEmpty()) {
        throw new InvalidArgException("The species ID is not valid");
    }

    // Create a new species object
    Species newSpecies = new Species(speciesId, name);
    // Add the new species to the species list
    _speciesList.add(newSpecies);
}

    /**
 * Retrieves the list of all employees in the hotel.
 *
 * @return A list of `Employee` objects.
 */
public List<Employee> getEmployees() {
    return _employeesList;
}


    /**
 * Registers a new employee in the system.
 *
 * @param employeeId The ID of the employee.
 * @param name       The name of the employee.
 * @param empType    The type of the employee (e.g., "VET" for Veterinary, "TRT" for Zookeeper).
 * @throws InvalidArgException   if any of the arguments are invalid.
 * @throws DuplicateKeyException if the employee ID already exists.
 */
public void registerEmployee(String employeeId, String name, String empType) throws InvalidArgException, DuplicateKeyException {
    // Check if the employee ID is valid
    if (employeeId == null || employeeId.isEmpty()) {
        throw new InvalidArgException("Employee's ID can't be null");
    }

    // Check if the employee's name is valid
    if (name == null || name.isEmpty()) {
        throw new InvalidArgException("Employee's name can't be null");
    }

    // Check if the employee type is valid
    if (empType == null || empType.isEmpty()) {
        throw new InvalidArgException("Employee's type can't be that one");
    }

    // Check if the employee ID already exists
    if (hasEmployee(employeeId)) {
        throw new DuplicateKeyException("Employee's ID already used");
    }

    // Register the employee based on their type
    if (empType.equals("VET")) {
        Veterinary newVeterinary = new Veterinary(employeeId, name);
        _veterinarysList.add(newVeterinary);
        _employeesList.add(newVeterinary);
    } else if (empType.equals("TRT")) {
        Zookeeper newZookeeper = new Zookeeper(employeeId, name);
        _zookeepersList.add(newZookeeper);
        _employeesList.add(newZookeeper);
    }
}

    /**
 * Retrieves the count of animals of a specific species in a given habitat.
 *
 * @param habitatId The ID of the habitat.
 * @param speciesId The ID of the species.
 * @return The count of animals of the specified species in the habitat.
 */
public int getEqualSpeciesInHabitat(String habitatId, String speciesId) {
    // Retrieve the habitat by its ID
    Habitat habitat = getHabitatById(habitatId);
    // Get the list of animals in the habitat
    List<Animals> animals = habitat.getListAnimalsInHabitat();
    int count = 0;
    // Iterate through the list of animals
    for (Animals animal : animals) {
        // Check if the current animal's species ID matches the given species ID (case-insensitive)
        if (animal.getAnimalSpecie().equalsIgnoreCase(speciesId)) {
            count++; // Increment the count
        }
    }
    return count; // Return the count of animals of the specified species in the habitat
}


    /**
 * Adds a responsibility to an employee.
 *
 * @param employeeId    The ID of the employee.
 * @param responsibility The responsibility to be added.
 * @throws InvalidArgException if the responsibility is invalid or not allowed for the employee type.
 * @throws UnknownKeyException if the employee or responsibility does not exist.
 */
public void addResponsibility(String employeeId, String responsibility) throws InvalidArgException, UnknownKeyException {
    // Check if the responsibility is valid
    if (responsibility == null || responsibility.isEmpty()) {
        throw new InvalidArgException("Employee without any responsibility to add");
    }

    // Retrieve the employee by their ID
    Employee employee = getEmployeeById(employeeId);

    // Check if the employee exists
    if (employee == null) {
        throw new UnknownKeyException(employeeId);
    }

    // Check if the responsibility exists
    if (!responsibilityIsHabitat(responsibility) && !responsibilityIsSpecies(responsibility)) {
        throw new UnknownKeyException("Responsibility doesn't exist");
    }

    // Check if the responsibility is valid for the employee type
    if (employee instanceof Veterinary && responsibilityIsHabitat(responsibility)) {
        throw new InvalidArgException("Veterinary can't have habitat as responsibility");
    }
    if (employee instanceof Zookeeper && responsibilityIsSpecies(responsibility)) {
        throw new InvalidArgException("Zookeeper can't have species as responsibility");
    }

    // Add the responsibility if the employee does not already have it
    if (!hasresponsibility(employee, responsibility)) {
        employee.addResponsibility(responsibility);
    }
}

    /**
 * Removes a responsibility from an employee.
 *
 * @param employeeId    The ID of the employee.
 * @param responsibility The responsibility to be removed.
 * @throws InvalidArgException if the responsibility is invalid or not allowed for the employee type.
 * @throws UnknownKeyException if the employee or responsibility does not exist.
 */
public void removeResponsibility(String employeeId, String responsibility) throws InvalidArgException, UnknownKeyException {
    // Check if the responsibility is valid
    if (responsibility == null || responsibility.isEmpty()) {
        throw new InvalidArgException("Employee without any responsibility to add");
    }

    // Retrieve the employee by their ID
    Employee employee = getEmployeeById(employeeId);

    // Check if the employee exists
    if (employee == null) {
        throw new UnknownKeyException(employeeId);
    }

    // Check if the responsibility exists
    if (!responsibilityIsHabitat(responsibility) && !responsibilityIsSpecies(responsibility)) {
        throw new UnknownKeyException(responsibility);
    }

    // Check if the responsibility is valid for the employee type
    if (employee instanceof Veterinary && responsibilityIsHabitat(responsibility)) {
        throw new InvalidArgException("Veterinary can't have habitat as responsibility");
    }
    if (employee instanceof Zookeeper && responsibilityIsSpecies(responsibility)) {
        throw new InvalidArgException("Zookeeper can't have species as responsibility");
    }

    // Remove the responsibility if the employee has it
    if (hasresponsibility(employee, responsibility)) {
        employee.removeResponsibility(responsibility);
    } else {
        throw new UnknownKeyException("Responsibility doesn't exist");
    }
}

    /**
 * Retrieves an employee from the system by their ID.
 *
 * @param idEmployee The ID of the employee.
 * @return The `Employee` object with the specified ID if found; `null` otherwise.
 */
public Employee getEmployeeById(String idEmployee) {
    // Iterate through the list of employees
    for (Employee emp : _employeesList) {
        // Check if the current employee's ID matches the given ID (case-insensitive)
        if (emp.getEmployeeId().equalsIgnoreCase(idEmployee)) {
            return emp; // Return the employee object
        }
    }
    return null; // Return null if the employee is not found
}

    /**
 * Checks if an employee has a specific responsibility.
 *
 * @param employee      The employee object.
 * @param responsibility The responsibility to check.
 * @return `true` if the employee has the specified responsibility; `false` otherwise.
 */
public boolean hasresponsibility(Employee employee, String responsibility) {
    List<String> responsibilities = employee.getResponsibility();
    for (String res : responsibilities) {
        if (res.equalsIgnoreCase(responsibility)) {
            return true;
        }
    }
    return false;
}

/**
 * Retrieves the list of responsibilities for a specific employee.
 *
 * @param employeeId The ID of the employee.
 * @return A list of responsibilities for the specified employee.
 */
public List<String> getResponsibilityEmp(String employeeId) {
    Employee employee = getEmployeeById(employeeId);
    return employee.getResponsibility();
}

/**
 * Retrieves the list of all vaccines in the hotel.
 *
 * @return A list of `Vaccine` objects.
 */
public List<Vaccine> getVaccines() {
    return _vaccinesList;
}

/**
 * Verifies if a vaccine with the given ID exists in the vaccine list.
 *
 * @param vaccineId The ID of the vaccine to check.
 * @return `true` if a vaccine with the specified ID exists; `false` otherwise.
 */
public boolean verifyVaccine(String vaccineId) {
    for (Vaccine tempvaccine : _vaccinesList) {
        if (tempvaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
            return true;
        }
    }
    return false;
}

/**
 * Registers a new vaccine in the system.
 *
 * @param vaccineId  The ID of the vaccine.
 * @param name       The name of the vaccine.
 * @param speciesIds An array of species IDs that the vaccine is effective against.
 * @throws InvalidArgException   if any of the arguments are invalid.
 * @throws DuplicateKeyException if the vaccine ID already exists.
 * @throws UnknownKeyException   if any of the species IDs do not exist.
 */
public void registerVaccine(String vaccineId, String name, String[] speciesIds) throws InvalidArgException, DuplicateKeyException, UnknownKeyException {
    // Check if the vaccine ID is valid
    if (vaccineId == null || vaccineId.isEmpty()) {
        throw new InvalidArgException("The vaccine ID is not valid");
    }

    // Check if the vaccine name is valid
    if (name == null || name.isEmpty()) {
        throw new InvalidArgException("Vaccine's name is not valid");
    }

    // Check if the species IDs array is valid
    if (speciesIds == null || speciesIds.length == 0) {
        throw new InvalidArgException("Vaccine without any species");
    }

    // Check if the vaccine ID already exists
    if (verifyVaccine(vaccineId)) {
        throw new DuplicateKeyException("Vaccine ID already exists");
    }

    // Check if all species IDs exist
    List<String> speciesIdList = new ArrayList<>();
    for (String speciesId : speciesIds) {
        Species species = getSpeciesById(speciesId, _speciesList); // Assuming this method exists and returns a Species object
        if (species == null) {
            throw new UnknownKeyException(speciesId);
        }
        speciesIdList.add(species.getSpeciesId());
    }

    // Create a new vaccine object
    Vaccine newVaccine = new Vaccine(vaccineId, name, speciesIdList);

    // Add the new vaccine to the vaccine list
    _vaccinesList.add(newVaccine);
}

   /**
 * Verifies if a vaccine with the given ID exists in the vaccine list.
 *
 * @param vaccineId The ID of the vaccine to check.
 * @return `true` if a vaccine with the specified ID exists; `false` otherwise.
 */
public boolean verifyVaccineId(String vaccineId) {
    // Iterate through the list of vaccines
    for (Vaccine vaccine : _vaccinesList) {
        // Check if the current vaccine's ID matches the given ID (case-insensitive)
        if (vaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
            return true; // Vaccine ID found
        }
    }
    return false; // Vaccine ID not found
}

    /**
 * Creates a new tree and adds it to the tree list.
 *
 * @param TreeId The ID of the tree.
 * @param name The name of the tree.
 * @param type The type of the tree (e.g., "CADUCA" for deciduous, other values for evergreen).
 * @param age The age of the tree.
 * @param diff The difficulty level of maintaining the tree.
 * @throws InvalidArgException if any of the arguments are invalid.
 */
public void createTree(String TreeId, String name, String type, int age, int diff) throws InvalidArgException {
    // Check if the arguments are correct
    if (TreeId == null || TreeId.isEmpty()) {
        throw new InvalidArgException("The tree ID is not valid");
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

    // Create tree based on type
    if (type.equals("CADUCA")) {
        DecidiousTree newTree = new DecidiousTree(_currentSeason, age, diff, TreeId, name);
        _treesList.add(newTree);
    } else {
        EvergreenTree newTree = new EvergreenTree(_currentSeason, age, diff, TreeId, name);
        _treesList.add(newTree);
    }
}

    /**
 * Retrieves a tree from the system by its ID.
 *
 * @param treeId The ID of the tree.
 * @return The `Tree` object with the specified ID if found; `null` otherwise.
 */
public Tree getTreeById(String treeId) {
    // Iterate through the list of trees
    for (Tree tree : _treesList) {
        // Check if the current tree's ID matches the given ID (case-insensitive)
        if (tree.getId().equalsIgnoreCase(treeId)) {
            return tree; // Return the tree object
        }
    }
    return null; // Return null if the tree is not found
}

    /**
 * Retrieves the cycle of a tree by its ID.
 *
 * @param treeId The ID of the tree.
 * @return The cycle of the tree.
 */
public String getTreeCicle(String treeId) {
    // Retrieve the tree by its ID
    Tree tree = getTreeById(treeId);
    // Return the cycle of the tree
    return tree.getCicle();
}

    /**
 * Verifies if a tree with the given ID exists in the tree list.
 *
 * @param treeId The ID of the tree to check.
 * @return `null` if no tree with the specified ID exists.
 * @throws DuplicateKeyException if a tree with the specified ID already exists.
 */
public String verifyTree(String treeId) throws DuplicateKeyException {
    // Iterate through the list of trees
    for (Tree tree : _treesList) {
        // Check if the current tree's ID matches the given ID (case-insensitive)
        if (tree.getId().equalsIgnoreCase(treeId)) {
            // Throw an exception if the tree ID already exists
            throw new DuplicateKeyException(treeId);
        }
    }
    // Return null if the tree ID does not exist
    return null;
}

    /**
 * Retrieves the list of all trees in the hotel.
 *
 * @return A list of `Tree` objects.
 */
public List<Tree> getTrees() {
    return _treesList;
}

    /**
 * Retrieves the list of all habitats in the hotel.
 *
 * @return A list of `Habitat` objects.
 */
public List<Habitat> getHabitats() {
    return _habitatsList;
}

/**
 * Registers a new habitat in the system.
 *
 * @param habitatId The ID of the habitat.
 * @param name      The name of the habitat.
 * @param area      The area of the habitat.
 * @return The newly created `Habitat` object.
 * @throws InvalidArgException   if any of the arguments are invalid.
 * @throws DuplicateKeyException if the habitat ID already exists.
 */
public Habitat registerHabitat(String habitatId, String name, int area) throws InvalidArgException, DuplicateKeyException {
    // Check if the arguments are correct
    if (habitatId == null || habitatId.isEmpty()) {
        throw new InvalidArgException("The habitat ID is not valid");
    }

    if (name == null || name.isEmpty()) {
        throw new InvalidArgException("Habitat's name is not valid");
    }

    if (area <= 0) {
        throw new InvalidArgException("Habitat's area is not valid");
    }

    // Check if habitat ID already exists
    for (Habitat auxHabitatId : _habitatsList) {
        if (auxHabitatId.getHabitatId().equalsIgnoreCase(habitatId)) {
            throw new DuplicateKeyException("The habitat ID already exists");
        }
    }

    // Create habitat
    Habitat newHabitat = new Habitat(habitatId, name, area);
    _habitatsList.add(newHabitat);

    return newHabitat;
}

    /**
 * Adds a tree to a habitat.
 *
 * @param hab     The ID of the habitat.
 * @param treeKey The ID of the tree to be added.
 */
public void addTreeToHabitat(String hab, String treeKey) {
    // Retrieve the habitat by its ID
    Habitat habitat = getHabitatById(hab);
    // Add the tree to the habitat
    habitat.addTreeToHabitat(treeKey);
}

   /**
 * Checks if a vaccine with the given ID exists in the vaccine list.
 *
 * @param vaccineId The ID of the vaccine to check.
 * @return `true` if a vaccine with the specified ID exists; `false` otherwise.
 */
public boolean existsVaccine(String vaccineId) {
    // Iterate through the list of vaccines
    for (Vaccine vaccine : _vaccinesList) {
        // Check if the current vaccine ID matches the given ID (case-insensitive)
        if (vaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
            return true; // Vaccine ID found
        }
    }
    return false; // Vaccine ID not found
}

    /**
 * Vaccinates an animal using a specified vaccine and veterinary.
 *
 * @param animalId     The ID of the animal to be vaccinated.
 * @param VeterinaryId The ID of the veterinary performing the vaccination.
 * @param VaccineId    The ID of the vaccine to be used.
 * @throws InvalidArgException         if the vaccine is not found.
 * @throws UnknownKeyException         if the employee is not a veterinary.
 * @throws CoreVaccineNotForVetException if the veterinary is not able to vaccinate the animal.
 */
public void VaccinateAnimal(String animalId, String VeterinaryId, String VaccineId) throws InvalidArgException, UnknownKeyException, CoreVaccineNotForVetException {
    // Retrieve the employee by their ID and check if they are a veterinary
    Employee employee = getEmployeeById(VeterinaryId);
    if (!(employee instanceof Veterinary)) {
        throw new UnknownKeyException("Employee is not a Veterinary");
    }

    // Retrieve the vaccine and animal by their IDs
    Vaccine vaccine = getVaccineById(VaccineId);
    Animals animal = getAnimalById(animalId);
    String animalSpecie = animal.getAnimalSpecie();

    // Check if the vaccine exists
    if (!existsVaccine(VaccineId)) {
        throw new InvalidArgException("Vaccine not found");
    }

    // Check if the veterinary is able to vaccinate the animal
    if (!verifyVeterinaryAbleToVaccinate(VeterinaryId, VaccineId)) {
        int count = 0;
        // Calculate the difference in characters between the animal's species and the vaccine's species
        for (String aux : vaccine.getSpecies()) {
            count += countDifferentCharacters(animal.getAnimalSpecie(), aux);
        }
        // Update the animal's health state based on the calculated difference
        String aux = addHealthState(animalId, count, vaccine);
        animal.setAnimalState(aux);
        throw new CoreVaccineNotForVetException("Veterinary not able to vaccinate");
    } else {
        // Log the vaccination as normal and update the animal's state
        vaccine.addDamageLog(animalId, "NORMAL");
        animal.setAnimalState("NORMAL");
    }

    // Record the vaccination result
    _vaccinationRes.add(VaccineId);
    _vaccinationRes.add(VeterinaryId);
    _vaccinationRes.add(animalSpecie);
    vaccine.addVaccineAplication();
}

    /**
 * Retrieves the vaccination registration list.
 *
 * @return A list of vaccination registrations.
 */
public List<String> getVaccinationResgistration() {
    return _vaccinationRes;
}

/**
 * Adds a health state to an animal based on the number of differences and logs the damage.
 *
 * @param animalId The ID of the animal.
 * @param num      The number of differences.
 * @param vaccine  The vaccine object.
 * @return The damage state of the animal.
 */
public String addHealthState(String animalId, int num, Vaccine vaccine) {
    String damage = "";
    if (num == 0) {
        damage = "CONFUSÃO";
    } else if (num >= 1 && num <= 4) {
        damage = "ACIDENTE";
    } else if (num >= 5) {
        damage = "ERRO";
    }
    vaccine.addDamageLog(animalId, damage);
    return damage;
}

    /**
 * Counts the number of distinct characters between two strings.
 *
 * @param str1 The first string.
 * @param str2 The second string.
 * @return The total number of distinct characters between the two strings.
 */
public static int countDifferentCharacters(String str1, String str2) {
    Set<Character> set1 = new HashSet<>();
    Set<Character> set2 = new HashSet<>();

    // Add characters of each string to their respective sets
    for (char ch : str1.toCharArray()) {
        set1.add(ch);
    }

    for (char ch : str2.toCharArray()) {
        set2.add(ch);
    }

    // Create a set for common characters
    Set<Character> commonCharacters = new HashSet<>(set1);

    // Retain only the common characters in both sets
    commonCharacters.retainAll(set2);

    // Calculate the total number of distinct characters
    int totalUniqueCharacters = Math.max(str1.length(), str2.length()) - commonCharacters.size();

    // Return the total number of distinct characters
    return totalUniqueCharacters;
}


    /**
 * Compares the names of two species and returns the number of different characters.
 *
 * @param specieIdvaccine The ID of the first species (vaccine).
 * @param specieId2       The ID of the second species.
 * @return The number of different characters between the two species names.
 */
public int differentSpeciesName(String specieIdvaccine, String specieId2) {
    // Create a HashMap to store the character counts of the vaccine species name
    HashMap<String, Integer> vaccine = new HashMap<>();

    // Convert the species IDs to character arrays
    char[] vaccineName = specieIdvaccine.toCharArray();
    char[] speciesName = specieId2.toCharArray();

    // Return 0 as the method is not yet implemented
    return 0;
}


    /**
 * Verifies if a veterinary is able to vaccinate an animal with a specified vaccine.
 *
 * @param veterinaryId The ID of the veterinary.
 * @param vaccineId    The ID of the vaccine.
 * @return `true` if the veterinary can vaccinate the animal with the specified vaccine; `false` otherwise.
 */
public boolean verifyVeterinaryAbleToVaccinate(String veterinaryId, String vaccineId) {
    // Retrieve the vaccine by its ID
    Vaccine vaccine = getVaccineById(vaccineId);
    // Retrieve the veterinary by their ID
    Veterinary vet = (Veterinary) getEmployeeById(veterinaryId);
    // Get the list of species the veterinary is responsible for
    List<String> species = vet.getResponsibility();
    // Check if the vaccine is effective against any of the species the veterinary is responsible for
    for (String specie : species) {
        if (vaccine.getSpecies().contains(specie)) {
            return true;
        }
    }
    return false;
}

    /**
 * Retrieves a vaccine from the system by its ID.
 *
 * @param vaccineId The ID of the vaccine.
 * @return The `Vaccine` object with the specified ID if found; `null` otherwise.
 */
public Vaccine getVaccineById(String vaccineId) {
    // Iterate through the list of vaccines
    for (Vaccine vaccine : _vaccinesList) {
        // Check if the current vaccine ID matches the given ID (case-insensitive)
        if (vaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
            return vaccine; // Return the vaccine if found
        }
    }
    return null; // Return null if the vaccine is not found
}

   /**
 * Changes the influence of a species in a specific habitat.
 *
 * @param habitatId The ID of the habitat.
 * @param speciesId The ID of the species.
 * @param influence The new influence value for the species in the habitat.
 * @throws CoreUnknownSpeciesIdException if the species ID is not found in the system.
 * @throws CoreUnknownHabitatKey if the habitat ID is not found in the system.
 */
public void changeInfluenceSpecies(String habitatId, String speciesId, String influence) throws CoreUnknownSpeciesIdException, CoreUnknownHabitatKey {
    // Retrieve the habitat by its ID
    Habitat habitat = getHabitatById(habitatId);
    if (habitat == null) {
        throw new CoreUnknownHabitatKey("Habitat not found");
    }
    // Check if the species exists
    if (!hasSpecies(speciesId)) {
        throw new CoreUnknownSpeciesIdException("Species not found");
    }
    // Change the influence of the species in the habitat
    habitat.changeInfluenceSpecies(speciesId, influence);
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

    /**
     * Advances the current season to the next season in the cycle.
     * Updates the season for all trees in the tree list.
     *
     * @return The new current season after advancing.
     */
    public Season advanceSeason() {
        for (Tree tree : _treesList) {
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

