package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentTm {
    private String id;
    private String time;
    private String date;
    private String problem;
    private String doctorName;
    private String patientName;
    private JFXButton deleteRecord;
}