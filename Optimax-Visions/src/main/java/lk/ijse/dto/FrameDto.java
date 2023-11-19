package lk.ijse.dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class FrameDto {
    private String id;
    private String name;
    private String type;
    private String glass;
    private String faceShape;
    private String frameShape;
    private String color;
    private String material;
    private int qtyOnHand;
    private double price;
}