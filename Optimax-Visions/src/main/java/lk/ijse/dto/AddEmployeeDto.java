package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class AddEmployeeDto {
    private String nicNumber;
    private String name;
    private String jobTitle;
    private LocalDate dob;
    private int telNo;
    private double basicSalary;
    private String profileLink;
}