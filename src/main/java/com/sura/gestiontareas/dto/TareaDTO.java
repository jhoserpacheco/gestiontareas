package com.sura.gestiontareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {

    @JsonProperty("tarea")
    private TareaIdDTO idTareaDto;

    @NotBlank(message = "La descripcion no puede ser vacia")
    @JsonProperty("descripcion")
    private String descripcionDto;

    @NotNull(message = "El usuario no puede ser vacio")
    @JsonProperty("usuario")
    private UsuarioDTO usuarioDto;

    @NotBlank(message = "La fecha de inicio no puede ser vacia")
    @JsonProperty("fecha_inicio")
    private String fechaInicioDto;

    @JsonProperty("fecha_fin")
    private String fechaFinDto;

}
