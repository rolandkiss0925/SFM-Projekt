package hu.unideb.inf.model;

import java.util.List;

public interface FoodDAO extends AutoCloseable{

    public void saveFood(Food f);
    public void deleteFood(Food f);
    public void updateFood(Food f);
    public List<Food> getFoods();
    public default void saveRestaurant(Restaurant r){
        throw new UnsupportedOperationException();
    }
}

