package com.sura.gestiontareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TareaIdDTO {

    @JsonProperty("id_tarea")
    private int idTarea;

    @JsonProperty("id_usuario")
    private int idUsuario;


}