package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
    private String refreshToken;
    //private String estado;

}
