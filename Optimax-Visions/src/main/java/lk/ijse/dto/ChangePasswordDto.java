package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class ChangePasswordDto {
    private String password;
    private String confirmPassword;
}