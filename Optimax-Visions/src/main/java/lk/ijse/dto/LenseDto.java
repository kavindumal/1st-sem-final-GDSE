package lk.ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class LenseDto {
    private String id;
    private String name;
    private String type;
    private int qty;
    private Double price;
    private JFXButton update;
    private JFXButton remove;
}