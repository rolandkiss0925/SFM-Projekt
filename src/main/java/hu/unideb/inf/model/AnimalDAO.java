package hu.unideb.inf.model;

import java.util.List;

public interface AnimalDAO extends AutoCloseable{
    //CRUD methods
    public void saveAnimal(Animal a); //C
    public void deleteAnimal(Animal a); //D
    public void updateAnimal(Animal a); //U
    public List<Animal> getAnimals(); //R
    public default void saveZoo(Zoo zoo){
        throw new UnsupportedOperationException();
    }
}
