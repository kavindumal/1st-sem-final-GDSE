package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OtpDto {
    private String otpOneText;
    private String otpTwoText;
    private String otpThreeText;
    private String otpFourText;
}