package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

import java.util.ArrayList;
import java.util.List;

public class Drink {
    private String name;
    private String size;
    private int sugar;
    private int ice;
    private final List<String> toppings = new ArrayList<>();
    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getIce() {
        return ice;
    }

    public void setIce(int ice) {
        this.ice = ice;
    }

    public List<String> getToppings() {
        return List.copyOf(toppings);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void addTopping(String topping) {
        this.toppings.add(topping);
    }
}
