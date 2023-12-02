package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class PrescriptionTm {
    private String lenseName;
    private String frameType;
    private String patientId;
    private String patientName;
    private LocalTime time;
    private LocalDate date;
    private JFXButton button;
}