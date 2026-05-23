package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

public class CoffeeBuilder implements DrinkBuilder {
    private final Drink drink;

    public CoffeeBuilder() {
        drink = new Drink();
        drink.setName("C\u00e0 ph\u00ea");
    }

    @Override
    public DrinkBuilder setName(String name) {
        drink.setName(name);
        return this;
    }

    @Override
    public DrinkBuilder setSize(String size) {
        drink.setSize(size);
        return this;
    }

    @Override
    public DrinkBuilder setSugar(int sugar) {
        drink.setSugar(sugar);
        return this;
    }

    @Override
    public DrinkBuilder setIce(int ice) {
        drink.setIce(ice);
        return this;
    }

    @Override
    public DrinkBuilder addTopping(String topping) {
        drink.addTopping(topping);
        return this;
    }

    @Override
    public DrinkBuilder setNote(String note) {
        drink.setNote(note);
        return this;
    }

    @Override
    public Drink build() {
        return drink;
    }
}
