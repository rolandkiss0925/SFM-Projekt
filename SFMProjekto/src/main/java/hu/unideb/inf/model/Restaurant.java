package hu.unideb.inf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Table(name = "Restaurant")
@Entity
public class Restaurant {
    @Id
    @GeneratedValue
    private int Rid;
    private String Rname;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_restaurant")
    private List<Food> foods = new ArrayList<>();

    public int getId() {
        return Rid;
    }

    public void setId(int id) {
        this.Rid = id;
    }

    public String getName() {
        return Rname;
    }

    public void setName(String name) {
        this.Rname = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
