package hu.unideb.inf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Users implements Serializable {
    private String name;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /*public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }*/

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
