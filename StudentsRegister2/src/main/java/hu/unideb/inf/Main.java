package hu.unideb.inf;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainApp.main(args);

        String[] Hamburger = {"Retro Burger", "Sajtos burger"};
        String[] pizza = {"pikáns pizza ", "Kukoricás pizza", "Gombás pizza"};
        String[] gyros = {"gyros tál", "Gyros pitában"};
        String[] names = {"john", "emma", "rick", "tim"};
        ArrayList<String> list = new ArrayList();
        kajalista(list,pizza);

    }

    private static void kajalista(ArrayList<String> list, String[] pizza) {
        List<String> lista = new ArrayList<>();

        for(String e: pizza){
            lista.add(e);
        }
    }
}
