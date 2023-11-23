package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PatientTm {
    private String id;
    private String name;
    private String email;
    private String family;
    private String tel;
    private JFXButton update;
    private JFXButton delete;
}
