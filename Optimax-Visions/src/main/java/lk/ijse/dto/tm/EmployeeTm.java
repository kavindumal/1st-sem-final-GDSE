package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class EmployeeTm {
    private String nicNumber;
    private String name;
    private String jobTitle;
    private int age;
    private int telNo;
    private JFXButton updateBtn;
    private JFXButton removeBtn;
    private JFXButton viewBtn;
}