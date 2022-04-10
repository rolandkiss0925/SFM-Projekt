package hu.unideb.inf.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAnimalDAO implements AnimalDAO{

    private List<Animal> animals;

    public FileAnimalDAO() {
        //deserialization
        try (FileInputStream fis = new FileInputStream("animals.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);){
            animals = (List<Animal>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            animals = new ArrayList<>();
        }
    }

    /**
     * Serializes the list of animals
     */
    private void serialize(){
        try (FileOutputStream fos = new FileOutputStream("animals.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(animals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAnimal(Animal a) {
        if (!animals.contains(a)) animals.add(a);
        serialize();
    }

    @Override
    public void deleteAnimal(Animal a) {
        animals.remove(a);
        serialize();
    }

    @Override
    public void updateAnimal(Animal a) {
        animals.remove(a); //change the equals method of the Animal to have a proper working
        animals.add(a);
        serialize();
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public void close() throws Exception {
        serialize();
    }
}
