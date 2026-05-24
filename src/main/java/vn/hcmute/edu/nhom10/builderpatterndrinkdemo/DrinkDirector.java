package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

public class DrinkDirector {
    public Drink makeDefaultMilkTea() {
        return new MilkTeaBuilder()
                .setSize("L")
                .setSugar(50)
                .setIce(70)
                .addTopping("Trân châu đen")
                .addTopping("Thạch phô mai")
                .setNote("Ít ngọt")
                .build();
    }

    public Drink makeDefaultCoffee() {
        return new CoffeeBuilder()
                .setSize("M")
                .setSugar(30)
                .setIce(50)
                .setNote("Đậm vị cà phê")
                .build();
    }
}
