package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class LenseTm {
    private String id;
    private String name;
    private String type;
    private int qty;
    private Double price;
    private JFXButton update;
    private JFXButton remove;
}