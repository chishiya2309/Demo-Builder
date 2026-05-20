package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Drink {
    private String name;
    private String size;
    private int sugar;
    private int ice;
    private final List<String> toppings = new ArrayList<>();
    private String note;

    public void addTopping(String topping) {
        this.toppings.add(topping);
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("THÔNG TIN ĐỒ UỐNG\n");
        sb.append("-------------------------\n");
        sb.append("Tên đồ uống: ").append(name).append("\n");
        sb.append("Kích thước: ").append(size).append("\n");
        sb.append("Mức đường: ").append(sugar).append("%\n");
        sb.append("Mức đá: ").append(ice).append("%\n");
        sb.append("Topping: ");
        if (toppings.isEmpty()) {
            sb.append("Không có\n");
        }else {
            sb.append(String.join(", ", toppings)).append("\n");
        }

        sb.append("Ghi chú: ")
                .append(note == null || note.isBlank() ? "Không có" : note)
                .append("\n");
        return sb.toString();
    }
}
