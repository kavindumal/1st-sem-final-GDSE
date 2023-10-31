package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RegisterDto {
    private String username;
    private String emailAddress;
    private String password;
    private String confirmPassword;
}
