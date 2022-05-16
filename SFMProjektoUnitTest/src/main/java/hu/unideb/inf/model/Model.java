package hu.unideb.inf.model;

public class Model {
    private Food pizza = new Food();
    private Food sb = new Food();
    private Food kola = new Food();
    private Food joker = new Food();

    public Model(){
    }

    public Food getPizza() {
        return pizza;
    }

    public Food getSb() {
        return sb;
    }

    public Food getKola() {
        return kola;
    }

    public Food getJoker() {
        return joker;
    }
}
