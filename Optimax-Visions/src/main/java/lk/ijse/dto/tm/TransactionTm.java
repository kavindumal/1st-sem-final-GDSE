package lk.ijse.dto.tm;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class TransactionTm {
    private String transactionId;
    private String transactionType;
    private String time;
    private String date;
    private String amount;
}
