package hu.unideb.inf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    public enum GenderType{
        MALE,FEMALE,NA
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}
