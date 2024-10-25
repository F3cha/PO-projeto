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
    private List<Vaccine> _vaccinesList;
    private List<String> _vaccinationRes;

    private Season _currentSeason;

    public Hotel() {
        speciesList = new ArrayList<>();
        animalList = new ArrayList<>();
        employeesList = new ArrayList<>();
        veterinaryList = new ArrayList<>();
        zookeeperList = new ArrayList<>();
        habitatsList = new ArrayList<>();
        treeList = new ArrayList<>();
        _vaccinesList = new ArrayList<>();
        _vaccinationRes = new ArrayList<>();
        _currentSeason = Season.Spring;


    }




    public int ShowAllSatisfaction(){
        int totalSatisfaction = 0;
        for (Employee emp : employeesList){
            try {
                totalSatisfaction += getSatisfactionOfEmployee(emp.getEmployeeId());
            }
            catch (UnknownKeyException e){
                e.printStackTrace();
            }
        }
        for (Animals animal : animalList){
            String animalId = animal.getAnimalId();
            try {
                totalSatisfaction += getAnimalSatisfaction(animalId);
            } catch (CoreUnknownAnimalKeyException e) {
                e.printStackTrace();
            }
        }
    return totalSatisfaction;


    }
    public String getSpeciesUsingAnimalId(String animalid) {
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equalsIgnoreCase(animalid)) {
                return animal.getAnimalSpecie();
            }
        }
        return null;
    }

    public boolean hasAnimal(String animalId) {
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return true;
            }
        }
        return false;
    }

    public void verifyAnimal(String animalId) throws UnknownKeyException {
        boolean found = false;
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(animalId);
        }
    }

    /**
     * Transfers an animal to a new habitat, updating its habitat ID.
     * 
     * @param animalId The ID of the animal.
     * @param habitatId The ID of the habitat
     * @throws CoreUnknownAnimalKeyException if the animal ID is not found in the system.
     * @throws CoreUnknownHabitatKey if the habitat ID is not found in the system.
     * 
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
        for (Habitat habitat : habitatsList) {
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
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return animal;
            }
        }
        return null;
    }

    /**
     * Verify if a specie exists.
     * 
     * @param speciesId The ID of the specie.
     * @return `true` if a specie with the specified ID exists; `false` otherwise. 
    */

    public boolean hasSpecies(String speciesId) {
        for (Species specie : speciesList) {
            if (specie.getSpeciesId().equalsIgnoreCase(speciesId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a species from the system by its ID.
     * 
     * @param idSpecies The ID of the animal.
     * @param allSpecies A List of Species
     * @return The "Species" object with the specified ID if found; `null` otherwise.
     * 
    */

    public Species getSpeciesById(String idSpecies, List<Species> allSpecies) {
        for (Species species : allSpecies) {
            if (species.getSpeciesId().equalsIgnoreCase(idSpecies)) {
                return species; // Retorna o objeto Species correspondente ao ID
            }
        }
        return null; // Retorna null se a espécie não for encontrada
    }

    /**
     * Retrieves a species from the system by its ID.
     * 
     * @param speciesName The name of the species.
     * @return The ID of the species if found.
     * @return 
     * 
    */

    public String returnIdbyNameSpecies(String speciesName) throws UnknownKeyException {
        for (Species specie : speciesList) {
            if (specie.getSpeciesName().equalsIgnoreCase(speciesName)) {
                return specie.getSpeciesId();
            }
        }
        throw new UnknownKeyException("Species not found");
    }


    public boolean hasEmployee(String employeeId) {
        for (Employee emp : employeesList) {
            if (emp.getEmployeeId().equalsIgnoreCase(employeeId)) {
                return true;
            }
        }
        return false;
    }

    /*Verifys*/

    public boolean verifyAnimalExistence(String animalId) {
        for (Animals animal : animalList) {
            if (animal.getAnimalId().equalsIgnoreCase(animalId)) {
                return true;
            }
        }
        return false;

    }

    public boolean responsibilityIsSpecies(String responsibility) {
        for (Species species : speciesList) {
            if (species.getSpeciesId().equalsIgnoreCase(responsibility)) {
                return true;
            }
        }
        return false;
    }

    public boolean responsibilityIsHabitat(String responsibility) {
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equalsIgnoreCase(responsibility)) {
                return true;
            }
        }
        return false;
    }

    public void verifyHabitat(String habitatId) throws UnknownKeyException {
        //FIXME mendonca verificadores nao mandam erros, as funcoes que os utilizam e que mandam
        boolean found = false;
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equalsIgnoreCase(habitatId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnknownKeyException(habitatId + " doesn't exist");
        }
    }

    public int getAnimalSatisfaction(String animalId) throws CoreUnknownAnimalKeyException {
        if (!verifyAnimalExistence(animalId)) {
            throw new CoreUnknownAnimalKeyException("Animal not found");
        }
        Animals animal = getAnimalById(animalId);
        String habitatId = animal.getAnimalHabitat();
        Habitat habitat = getHabitatById(habitatId);


        return Math.round(20 + 3 * getEqualSpeciesInHabitat(habitatId, animalId) - 2 * (getTotalAnimalsInHabitat(habitatId)
                - getEqualSpeciesInHabitat(habitatId, animalId) - 1) +
                ((float) habitat.getHabitatArea() / getTotalAnimalsInHabitat(habitatId)) + getInfluenceAnimalInHabiat(animalId, habitatId));
    }

    public int getZookeeperSatisfaction(String zookeeperId) {

        Zookeeper zookeeper = (Zookeeper) getEmployeeById(zookeeperId);
        float totalSatisfaction = 0;
        for (String habitatId : zookeeper.getResponsibility()) {
            totalSatisfaction += (workInHabitat(habitatId) / getWorkersInHabitat(habitatId));
        }


        return Math.round(300 - totalSatisfaction);
    }

    public int getSatisfactionOfEmployee(String employeeId) throws UnknownKeyException {
        if (!hasEmployee(employeeId)) {
            throw new UnknownKeyException("Employee not found");
        }
        Employee employee = getEmployeeById(employeeId);
        if (employee instanceof Zookeeper) {
            return getZookeeperSatisfaction(employeeId);
        } else{
            return getVeterinarySatisfaction(employeeId);
        }

    }

    public int getVeterinarySatisfaction(String veterinaryId) {
        return Math.round(20 - VeterinarySpecies(veterinaryId));
    }
    public float VeterinarySpecies(String veterinaryId){
        Veterinary vet = (Veterinary) getEmployeeById(veterinaryId);
        float totaldivisions=0;
        for (String speciesId : vet.getResponsibility()){
            int totalPopulation = getPopulationofSpecies(speciesId);
            int numberofVets = getVeterinarysResponsibleforSpecies(speciesId);
            totaldivisions += (totalPopulation/numberofVets);
        }
        return totaldivisions;

    }

    public int getPopulationofSpecies (String speciesId){
        int total = 0;
        for (Animals animal : animalList){
            if (animal.getAnimalSpecie().equalsIgnoreCase(speciesId)){
                total++;
            }
        }
        return total;
    }

    public int getVeterinarysResponsibleforSpecies(String speciesId){
        int total = 0;
        for (Employee emp : employeesList){
            if (emp instanceof Veterinary){
                if (emp.hasResponsibility(speciesId)){
                    total++;
                }
            }
        }
        if (total == 0){
            return 1;
        }
        return total;
    }

    public int getWorkersInHabitat(String habitatId) {
        int totalworkers = 0;
        for (Employee emp : employeesList) {
            if (emp instanceof Zookeeper) {
                if (hasresponsibility(emp, habitatId)) {
                    totalworkers++;

                }
            }
        }
        return totalworkers;
    }

    public int workInHabitat(String habitatID) {
        Habitat habitat = getHabitatById(habitatID);
        int area = habitat.getHabitatArea();
        int population = habitat.getListAnimalsInHabitat().size();
        int CleaningEffort = 0;
        for (String treeID : habitat.getHabitatTreeList()) {
            Tree tree = getTreeById(treeID);
            CleaningEffort += tree.getCleaningEffort();
        }
        return (area + population * 3 + CleaningEffort);
    }


    public int getTotalAnimalsInHabitat(String habitatId) {
        Habitat habitat = getHabitatById(habitatId);
        return habitat.getListAnimalsInHabitat().size();
    }

    public int getInfluenceAnimalInHabiat(String animalId, String habitatId) {
        String speciesUsingAnimalId = getSpeciesUsingAnimalId(animalId);
        String influence = getHabitatById(habitatId).getInfluenceSpecies(speciesUsingAnimalId);
        if (influence.equals("NEU")) {
            return 0;
        } else if (influence.equals("POS")) {
            return 20;
        } else {
            return -20;
        }

    }

    public List<Animals> getAnimals() {
        return animalList;
    }

    public boolean tryRegisterAnimal(String animalId, String nameAnimals, String habitatId, String speciesId) throws InvalidArgException, DuplicateKeyException {
        // checks if the arguments are correct.


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
        Habitat habitat = getHabitatById(habitatId);
        habitat.addAnimalToHabitat(newAnimal);
    }

    public Habitat getHabitatById(String habitatId) {
        for (Habitat habitat : habitatsList) {
            if (habitat.getHabitatId().equalsIgnoreCase(habitatId)) {
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
        return employeesList;
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

    public int getEqualSpeciesInHabitat(String habitatId, String speciesId) {
        Habitat habitat = getHabitatById(habitatId);
        List<Animals> animals = habitat.getListAnimalsInHabitat();
        int count = 0;
        for (Animals animal : animals) {
            if (animal.getAnimalSpecie().equalsIgnoreCase(speciesId)) {
                count++;
            }
        }
        return count;
    }


    public void addResponsibility(String employeeId, String responsibility) throws InvalidArgException, UnknownKeyException {
        if (responsibility == null || responsibility.isEmpty()) {
            throw new InvalidArgException("Employee without any responsibility to add");
        }

        Employee employee = getEmployeeById(employeeId);

        if (getEmployeeById(employeeId) == null) {
            throw new UnknownKeyException(employeeId);
        }
        if (!responsibilityIsHabitat(responsibility) && !responsibilityIsSpecies(responsibility)) {
            throw new UnknownKeyException("Responsibility doesn't exist");
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

    public void removeResponsibility(String employeeId, String responsibility) throws InvalidArgException, UnknownKeyException {
        // checks if the arguments are correct.
        if (responsibility == null || responsibility.isEmpty()) {
            throw new InvalidArgException("Employee without any responsibility to add");
        }
        Employee employee = getEmployeeById(employeeId);
        if (employee == null) {
            throw new UnknownKeyException(employeeId);
        }
        if (!responsibilityIsHabitat(responsibility) && !responsibilityIsSpecies(responsibility)) {
            throw new UnknownKeyException(responsibility);
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
            throw new UnknownKeyException("Responsibility doesn't exist");
        }

    }

    public Employee getEmployeeById(String idEmployee) {
        for (Employee emp : employeesList) {
            if (emp.getEmployeeId().equalsIgnoreCase(idEmployee)) {
                return emp;
            }
        }
        return null;
    }

    public boolean hasresponsibility(Employee employee, String responsibility) {
        List<String> responsibilities = employee.getResponsibility();
        for (String res : responsibilities) {
            if (res.equalsIgnoreCase(responsibility)) {
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
        return _vaccinesList;
    }

    public boolean verifyVaccine(String vaccineId) {
        for (Vaccine tempvaccine : _vaccinesList) {
            if (tempvaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
                return true;
            }
        }
        return false;
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
        if (verifyVaccineId(vaccineId)) {
            throw new DuplicateKeyException("Vaccine iD already exists");
        }

        // checks if vaccine iD already exists

        List<String> speciesIdList = new ArrayList<>();
        for (String speciesId : speciesIds) {
            Species species = getSpeciesById(speciesId, speciesList); // Assumindo que este método existe e retorna um objeto Species
            if (species == null) {
                throw new UnknownKeyException(speciesId);
            }
            speciesIdList.add(species.getSpeciesId());
        }


        Vaccine newVaccine = new Vaccine(vaccineId, name, speciesIdList);
        _vaccinesList.add(newVaccine);
    }

    public boolean verifyVaccineId(String vaccineId) {
        for (Vaccine vaccine : _vaccinesList) {
            if (vaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
                return true;
            }
        }
        return false;
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
            if (tree.getId().equalsIgnoreCase(treeId)) {
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
            if (tree.getId().equalsIgnoreCase(treeId)) {
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
            if (auxHabitatId.getHabitatId().equalsIgnoreCase(habitatId)) {
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

    public void VaccinateAnimal(String animalId, String VeterinaryId, String VaccineId) throws InvalidArgException, UnknownKeyException, CoreVaccineNotForVetException {
        Employee employee = getEmployeeById(VeterinaryId);
        if (!(employee instanceof Veterinary)) {
            throw new UnknownKeyException("Employee is not a Veterinary");
        }
        
        Vaccine vaccine = getVaccineById(VaccineId);
        Animals animal = getAnimalById(animalId);
        
        if (vaccine == null) {
            throw new InvalidArgException("Vaccine not found");
        }

        if (!verifyVeterinaryAbleToVaccinate(VeterinaryId, VaccineId)) {
            int count = 0;
            for(String aux: vaccine.getSpecies()) {
                count += countDifferentCharacters(animal.getAnimalSpecie(), aux);
            }

            addHealthState(animalId, count, vaccine);
            throw new CoreVaccineNotForVetException("Veterinary not able to vaccinate");
        } else {
            vaccine.addDamageLog(animalId, "NORMAL");
            
        }
        _vaccinationRes.add(VaccineId);
        _vaccinationRes.add(VeterinaryId);
        _vaccinationRes.add(animalId);

    }

    public List<String> getVaccinationResgistration() {
        return _vaccinationRes;
    }

    public void addHealthState(String animalId, int num, Vaccine vaccine) {
            String damage = "";
            if (num == 0) {
                damage = "CONFUSÃO";
            } else if (num >= 1 && num <= 4) {
                damage = "ACIDENTE";
            } else if (num >= 5) {
                damage = "ERRO";
            }
            vaccine.addDamageLog(animalId, damage);
    }

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

        //
        Set<Character> commonCharacters = new HashSet<>(set1);

        // same characteres 
        commonCharacters.retainAll(set2);

        int totalUniqueCharacteres = Math.max(str1.length(), str2.length()) - commonCharacters.size();

        // Total number of distinct characters
        return totalUniqueCharacteres;
    }
    

    public int differentSpeciesName(String specieIdvaccine, String specieId2){
        HashMap<String, Integer> vaccine = new HashMap<>();
        char[] vaccineName = specieIdvaccine.toCharArray();
        char[] speciesName = specieId2.toCharArray();

        return 0;
    }


    public boolean verifyVeterinaryAbleToVaccinate (String veterinaryId, String vaccineId) {
       Vaccine vaccine = getVaccineById(vaccineId);
         Veterinary vet = (Veterinary) getEmployeeById(veterinaryId);
            List<String> species = vet.getResponsibility();
            for (String specie : species){
                if (vaccine.getSpecies().contains(specie)){
                    return true;
                }
            }
            return false;
    }

    public Vaccine getVaccineById(String vaccineId) {
        for (Vaccine vaccine : _vaccinesList) {
            if (vaccine.getVaccineId().equalsIgnoreCase(vaccineId)) {
                return vaccine;
            }
        }
        return null;
    }

    public void changeInfluenceSpecies(String habitatId, String speciesId, String influence) throws CoreUnknownSpeciesIdException, CoreUnknownHabitatKey {
        Habitat habitat = getHabitatById(habitatId);
        if (habitat == null) {
            throw new CoreUnknownHabitatKey("Habitat not found");
        }
        if (!hasSpecies(speciesId)) {
            throw new CoreUnknownSpeciesIdException("Species not found");
        }
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

