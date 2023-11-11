package lk.ijse.dto;

import lombok.*;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class PatientDto {
    private String patientId;
    private String fullname;
    private String address;
    private String email;
    private String familyname;
    private String telNo;
}