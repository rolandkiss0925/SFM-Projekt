package hu.unideb.inf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Zoo {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_zoo")
    private List<Animal> animals = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
