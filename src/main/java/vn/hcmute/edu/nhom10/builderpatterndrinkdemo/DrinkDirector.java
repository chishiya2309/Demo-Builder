package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

public class DrinkDirector {
    public Drink makeDefaultMilkTea() {
        return new MilkTeaBuilder()
                .setSize("L")
                .setSugar(50)
                .setIce(70)
                .addTopping("Tr\u00e2n ch\u00e2u \u0111en")
                .addTopping("Th\u1ea1ch ph\u00f4 mai")
                .setNote("\u00cdt ng\u1ecdt")
                .build();
    }

    public Drink makeDefaultCoffee() {
        return new CoffeeBuilder()
                .setSize("M")
                .setSugar(30)
                .setIce(50)
                .setNote("\u0110\u1eadm v\u1ecb c\u00e0 ph\u00ea")
                .build();
    }
}
