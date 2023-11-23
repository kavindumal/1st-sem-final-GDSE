package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {
    private String id;
    private LocalTime time;
    private LocalDate date;
    private String problem;
    private String doctor;
    private String prescription;
    private String patientId;
    private String paymentId;
}
