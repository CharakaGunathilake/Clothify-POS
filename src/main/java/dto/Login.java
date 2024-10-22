package dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private String userId;
    private String email;
    private String password;

}
