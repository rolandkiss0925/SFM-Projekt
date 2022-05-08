package hu.unideb.inf.model;

public class Model {
    private Food pizza = new Food();
    private Food sb = new Food();
    private Food kola = new Food();
    private Food joker = new Food();

    public Model(){
        this.pizza.setName("Pizza");
        this.pizza.setPrice(1850);
        this.sb.setName("Sült Burgonya");
        this.sb.setPrice(650);
        this.kola.setName("Coca-cola");
        this.kola.setPrice(400);
        this.joker.setName("Húsvéti sonka");
        this.joker.setPrice(69);
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
