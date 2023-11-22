package com.sura.gestiontareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @JsonProperty("id_usuario")
    private Integer idUsuario;

    private String nombre;

    private String email;
}
