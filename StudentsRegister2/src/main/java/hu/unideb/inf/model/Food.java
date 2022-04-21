<<<<<<< HEAD
package hu.unideb.inf.model;

import java.util.ArrayList;

public class Food {


    String[] Hamburger = {"Retro Burger", "Sajtos burger"};
    String[] pizza = {"pikáns pizza ", "Kukoricás pizza", "Gombás pizza"};
    String[] gyros = {"gyros tál", "Gyros pitában"};
    String[] names = {"john", "emma", "rick", "tim"};
    ArrayList<String[]> list;

    public Food(){
        list = new ArrayList<String[]>();
        list.add(Hamburger);
        list.add(pizza);
    }

    public ArrayList<String[]> getList() {
        return list;
    }

}
=======
package hu.unideb.inf.model;

import java.util.ArrayList;

public class Food {


    String[] Hamburger = {"Retro Burger", "Sajtos burger"};
    String[] pizza = {"pikáns pizza ", "Kukoricás pizza", "Gombás pizza"};
    String[] gyros = {"gyros tál", "Gyros pitában"};
    String[] names = {"john", "emma", "rick", "tim"};
    ArrayList<String[]> list;

    public Food(){
        list = new ArrayList<String[]>();
        list.add(Hamburger);
        list.add(pizza);
    }

    public ArrayList<String[]> getList() {
        return list;
    }

}
>>>>>>> 9c321cd18c049469cbd4d980cf82919518eacf26
