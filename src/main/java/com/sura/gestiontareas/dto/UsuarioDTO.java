package com.sura.gestiontareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @JsonProperty("id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Email(message = "Email no puede estar vacío")
    private String email;
}
