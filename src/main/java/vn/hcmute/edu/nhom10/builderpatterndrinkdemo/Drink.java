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

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("TH\u00d4NG TIN \u0110\u1ed2 U\u1ed0NG\n");
        sb.append("-------------------------\n");
        sb.append("T\u00ean \u0111\u1ed3 u\u1ed1ng: ").append(name).append("\n");
        sb.append("K\u00edch th\u01b0\u1edbc: ").append(size).append("\n");
        sb.append("M\u1ee9c \u0111\u01b0\u1eddng: ").append(sugar).append("%\n");
        sb.append("M\u1ee9c \u0111\u00e1: ").append(ice).append("%\n");
        sb.append("Topping: ");
        if (toppings.isEmpty()) {
            sb.append("Kh\u00f4ng c\u00f3\n");
        } else {
            sb.append(String.join(", ", toppings)).append("\n");
        }

        sb.append("Ghi ch\u00fa: ")
                .append(note == null || note.isBlank() ? "Kh\u00f4ng c\u00f3" : note)
                .append("\n");
        return sb.toString();
    }
}
