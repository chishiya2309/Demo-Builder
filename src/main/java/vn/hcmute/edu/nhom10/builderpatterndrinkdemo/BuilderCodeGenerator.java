package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

import java.util.List;

public class BuilderCodeGenerator {

    public static String generateBuilderCode(Drink drink) {
        String builderClass = "Tr\u00e0 s\u1eefa".equals(drink.getName())
                ? "MilkTeaBuilder"
                : "CoffeeBuilder";

        StringBuilder sb = new StringBuilder();
        sb.append("// S\u1eed d\u1ee5ng Builder Pattern\n");
        sb.append("Drink drink = new ").append(builderClass).append("()\n");
        sb.append("        .setSize(\"").append(drink.getSize()).append("\")\n");
        sb.append("        .setSugar(").append(drink.getSugar()).append(")\n");
        sb.append("        .setIce(").append(drink.getIce()).append(")\n");

        List<String> toppings = drink.getToppings();
        for (String topping : toppings) {
            sb.append("        .addTopping(\"").append(topping).append("\")\n");
        }

        String note = drink.getNote();
        if (note != null && !note.isBlank()) {
            sb.append("        .setNote(\"").append(note).append("\")\n");
        }

        sb.append("        .build();");
        return sb.toString();
    }

    public static String generateDirectorCode(String drinkType) {
        StringBuilder sb = new StringBuilder();
        sb.append("// S\u1eed d\u1ee5ng Director Pattern\n");
        sb.append("DrinkDirector director = new DrinkDirector();\n");

        if ("Tr\u00e0 s\u1eefa".equals(drinkType)) {
            sb.append("Drink drink = director.makeDefaultMilkTea();");
        } else {
            sb.append("Drink drink = director.makeDefaultCoffee();");
        }

        return sb.toString();
    }
}
