package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
import javax.lang.model.util.ElementScanner14;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  // FIXME define attributes

  private String name;
  private List<Species> speciesList;
  private List<Animals> animalList;
  private List<Employee> employeesList;
  private List<Habitat> habitatsList;
  private List<Tree> treeList;
  private List<Vaccines> vaccinesList;

  // FIXME define contructor(s)

  public Hotel() { //not sure se é suposto ter nome
    this.speciesList = new ArrayList<>();
    this.animalList = new ArrayList<>();
    this.employeesList = new ArrayList<>();
    this.habitatsList = new ArrayList<>();
    this.treeList = new ArrayList<>();
    this.vaccinesList = new ArrayList<>();
    }

  // FIXME define more methods

  public void registerAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) throws OneOrMoreCoreExceptions {
    // checks if the arguments are correct.

    if (animalId == null || animalId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Animal's iD can't be null");
    }
    
    if (nameAnimals == null || nameAnimals.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Animal's name can't be null");
    }

    if (habitatId == null || habitatId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Animal's habitat can't be null");
    }

    if (speciesId == null || speciesId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Animal's species iD can't be null");
    }

  // checks if the iD of the new animal already exists.

  for (Animals animal: animalList) {
    if (animal.getAnimalId().equals(animalId)) {
      throw new OneOrMoreCoreExceptions("Animal's iD already used");
    }
  }

  // checks if specie exist.
  boolean speciesExists = false;

  for (Species specie: speciesList){
    if (specie.getSpeciesId().equals(speciesId)) {
      speciesExists = true;
      break;
    }
  }

  if(!speciesExists) {
    throw new  OneOrMoreCoreExceptions("Given specie doesn't exists.");
  }
  
  // create animal

  Animals newAnimal = new Animals(animalId, nameAnimals, habitatId, speciesId);
  animalList.add(newAnimal);

  }


  public void registerSpecies(String speciesId, String name) throws OneOrMoreCoreExceptions { 
    // checks if the arguments are correct.

    if(speciesId == null || speciesId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("The species iD is not valid");
    }

    if(name = null || name.isEmpty()) {
      throw new OneOrMoreCoreExceptions("The species name is not valid");
    }

    // checks if species already exists

    for(Species speciesId: speciesList) {
      if(speciesId.getSpeciesId().equals(speciesId)) {
        throw new OneOrMoreCoreExceptions("The species iD already exists");
      }
    }

    // create species

    Species newEspecies = new Species(speciesId, name);
    speciesList.add(newEspecies);

  }


  public void registerEmployee(String employeeId, String name, String empType) throws OneOrMoreCoreExceptions {
    Employee employee;

    // checks if the arguments are correct.
    if(employeeId == null || employeeId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("The employee iD is not valid");
    }

    if(name == null || name.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Employee's name is not valid");
    }

    if(empType == null || empType.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Employee's type is not valid");
    }

    // checks if employee iD already exists

    for(Employee empId: employeesList) {
      if(empId.getEmployeeId().equals(employeeId)) {
        throw new OneOrMoreCoreExceptions("The employee iD already exists");
      }
    }

    if(empType.equalsIgnoreCase("veterinary")) {
      employee = new Veterinary(employeeId, name);
    } else if (empType.equalsIgnoreCase("zookeeper")) {
      employee = new Zookeeper(employeeId, name);
    } else {
      throw new OneOrMoreCoreExceptions("Employee type not valid: " + empType);
    }

    employeesList.add(employee);
  }


  public void addResponsibility(String employeeId, String[] responsibility) throws OneOrMoreCoreExceptions {
    // checks if the arguments are correct.

    if(employeeId == null || employeeId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("The employee iD is not valid");
    }

    if(responsibility == null || responsibility.length() == 0) {
      throw new OneOrMoreCoreExceptions("Employee without any responsibility to add");
    }

    for(Employee emp: employeesList){
      if (emp.getEmployeeId().equals(employeeId)) {
        List<String> responsibilityList = Arrays.asList(responsibility);
        emp.setResponsability(responsibilityList);
      }
    }

  }


  public void registerVaccine(String vaccineId, String name, String[] speciesIds) throws someCoreExceptionsOneOrMoreCoreExceptions {
    // checks if the arguments are correct.

    if(vaccineId == null || vaccineId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("The vaccine iD is not valid");
    }

    if(name == null || name.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Vaccine's name is not valid");
    }

    if(speciesIds == null || vaccineId.length() == 0) {
      throw new OneOrMoreCoreExceptions("Vaccine without any species");
    }

    // checks if vaccine iD already exists

    for(Vaccine idVaccine: vaccinesList) {
      if(idVaccine.getVaccineId().equals(vaccineId)) {
        throw new OneOrMoreCoreExceptions("The vaccine iD already exists");
      }
    }


    List<String> speciesIdsList = Arrays.asList(speciesIds);
    Vaccine newVaccine = new Vaccine(vaccineId, name, speciesIdsList);
    vaccinesList.add(newVaccine);
  }


  public void createTree(String TreeId, String name, String type, int age, int diff) throws OneOrMoreCoreExceptions {
    Tree tree;

    Season season = Season.Autumn;

    if(type.equalsIgnoreCase("evergreen")) {
      tree = new EvergreenTree(season, age, diff);
    } else if (type.equalsIgnoreCase("decidious")) {
      tree = new DecidiousTree(season, age, diff);
    } else {
      throw new OneOrMoreCoreExceptions("Tree type not valid: " + type);
    }

    treeList.add(tree);
  }


  public Habitat registerHabitat(String habitatId, String name, int area) throws OneOrMoreCoreExceptions {
    // checks if the arguments are correct.
    if(habitatId == null || habitatId.isEmpty()) {
      throw new OneOrMoreCoreExceptions("The employee iD is not valid");
    }

    if(name == null || name.isEmpty()) {
      throw new OneOrMoreCoreExceptions("Employee's name is not valid");
    }

    if(area <= 0) {
      throw new OneOrMoreCoreExceptions("Employee's type is not valid");
    }

    // checks if habitat iD already exists

    for(Habitat auxHabitatId: habitatsList) {
      if(auxHabitatId.getHabitatId().equals(habitatId)) {
        throw new OneOrMoreCoreExceptions("The habitat iD already exists");
      }
    }

    // create habitat

    Habitat newHabitat = new Habitat(habitatId, name, area);
    habitatsList.add(newHabitat);

    return newHabitat;
  }


  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }
}
