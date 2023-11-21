package lk.ijse.dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class LenseDetailsDto {
    private String lenseId;
    private String name;
    private String typeFor;
    private int qty;
    private Double price;
}
