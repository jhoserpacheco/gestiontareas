package com.sura.gestiontareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {

    @JsonProperty("tarea")
    private TareaIdDTO idTareaDto;

    @JsonProperty("descripcion")
    private String descripcionDto;

    @JsonProperty("usuario")
    private UsuarioDTO usuarioDto;

    @JsonProperty("fecha_inicio")
    private String fechaInicioDto;

    @JsonProperty("fecha_fin")
    private String fechaFinDto;

}
