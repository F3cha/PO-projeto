package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  // FIXME define attributes

  private String name;
  private List<Species> speciesList;
  private List<Animals> animalList;
  private List<Employee> employeesList;

  // FIXME define contructor(s)

  public Hotel(String name) {
    this.name = name;
    this.speciesList = new ArrayList<>();
    this.animalList = new ArrayList<>();
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


  public void registerEmployee(String employeeId, String name, empType) throws OneOrMoreCoreExceptions {
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

  }


  public void addResponsibility(employeeId, responsibility) throws OneOrMoreCoreExceptions {

  }


  public void registerVaccine(vaccineId, name, String[] speciesIds) throws someCoreExceptionsOneOrMoreCoreExceptions {

  }


  public void createTree(TreeId, name, type, age, diff) throws OneOrMoreCoreExceptions {

  }


  public Habitat registerHabitat(habitatId, name, area) throws OneOrMoreCoreExceptions { 

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
