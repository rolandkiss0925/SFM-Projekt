/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Food_name;
    private int db = 1;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type { ETEL, ITAL, KORET }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Food_name;
    }
    public void setName(String name) {
        this.Food_name = name;
    }
    public int getDb() {
        return db;
    }
    public void setDb(int db) {
        this.db = db;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}