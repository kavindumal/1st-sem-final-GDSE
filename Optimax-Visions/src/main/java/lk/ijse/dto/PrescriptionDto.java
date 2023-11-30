package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrescriptionDto {
    private String patientId;
    private Double sphereRight;
    private Double sphereLeft;
    private Double cylRight;
    private Double cylLeft;
    private int axisRight;
    private int axisLeft;
    private Double addRight;
    private Double addLeft;
    private int pdDistance;
}
