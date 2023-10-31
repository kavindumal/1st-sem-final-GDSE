package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OtpVerificationDto {
    private String otpText1;
    private String otpText2;
    private String otpText3;
    private String otpText4;
}
