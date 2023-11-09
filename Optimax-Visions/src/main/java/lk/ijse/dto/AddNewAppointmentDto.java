package lk.ijse.dto;

import lombok.*;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class AddNewAppointmentDto {
    private String date;
    private String time;
    private String problem;
    private String doctor;
    private String prescription;
}
