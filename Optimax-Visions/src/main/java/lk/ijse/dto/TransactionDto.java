package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class TransactionDto {
    private String transactionId;
    private String transactionType;
    private LocalTime time;
    private LocalDate date;
    private Double amount;
}
