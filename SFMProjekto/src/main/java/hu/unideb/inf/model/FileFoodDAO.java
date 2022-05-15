package hu.unideb.inf.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFoodDAO implements FoodDAO{

    private List<Food> foods;
    private List<Restaurant> restaurants;

    public FileFoodDAO() {
        //deserialization
        try (FileInputStream fis = new FileInputStream("foods.ser");
             ObjectInputStream ois = new ObjectInputStream(fis);){
            foods = (List<Food>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            foods = new ArrayList<>();
        }
    }

    /**
     * Serializes the list of animals
     */
    private void serialize(){
        try (FileOutputStream fos = new FileOutputStream("foods.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(foods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveFood(Food f) {
        if (!foods.contains(f)) foods.add(f);
        serialize();
    }
    @Override
    public void deleteFood(Food f) {
        foods.remove(f);
        serialize();
    }
    @Override
    public void updateFood(Food f) {
        foods.remove(f); //change the equals method of the Animal to have a proper working
        foods.add(f);
        serialize();
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public List<Restaurant> getRestaurants(){
        return restaurants;
    }

    @Override
    public void close() throws Exception {
        serialize();
    }
}
