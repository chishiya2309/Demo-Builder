package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

public interface DrinkBuilder {
    DrinkBuilder setName(String name);

    DrinkBuilder setSize(String size);

    DrinkBuilder setSugar(int sugar);

    DrinkBuilder setIce(int ice);

    DrinkBuilder addTopping(String topping);

    DrinkBuilder setNote(String note);

    Drink build();
}
