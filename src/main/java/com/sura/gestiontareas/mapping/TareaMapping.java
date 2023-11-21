package com.sura.gestiontareas.mapping;

import com.sura.gestiontareas.dto.TareaDTO;
import com.sura.gestiontareas.dto.TareaIdDTO;
import com.sura.gestiontareas.entity.TareaEntity;
import com.sura.gestiontareas.entity.TareaIdEntity;
import com.sura.gestiontareas.util.LocalDateFomatter;

public class TareaMapping {

    public TareaEntity tareaDtoToTareaEntity(TareaDTO tareaDto){
        return TareaEntity.builder()
                .idTarea(tareaIdDtoToTareaIdEntity(tareaDto.getIdTareaDto()))
                .descripcion(tareaDto.getDescripcionDto())
                .idUsuario(new UsuarioMapping().usuarioDtoToUsuarioEntity(tareaDto.getUsuarioDto()))
                .fechaInicio(new LocalDateFomatter().dateStringtoLocalDate(tareaDto.getFechaInicioDto()))
                .fechaFin(new LocalDateFomatter().dateStringtoLocalDate(tareaDto.getFechaFinDto()))
                .build();
    }

    public TareaDTO tareaEntityToTareaDto(TareaEntity tareaEntity){
        return TareaDTO.builder()
                .idTareaDto(tareaIdEntityToTareaIdDto(tareaEntity.getIdTarea()))
                .descripcionDto(tareaEntity.getDescripcion())
                .usuarioDto(new UsuarioMapping().usuarioEntityToUsuarioDto(tareaEntity.getIdUsuario()))
                .fechaInicioDto(new LocalDateFomatter().localDateToStringDate(tareaEntity.getFechaInicio()))
                .fechaFinDto(new LocalDateFomatter().localDateToStringDate(tareaEntity.getFechaFin()))
                .build();
    }

    public TareaIdDTO tareaIdEntityToTareaIdDto(TareaIdEntity tareaIdEntity){
        return TareaIdDTO.builder()
                .idTarea(tareaIdEntity.getIdTarea())
                .idUsuario(tareaIdEntity.getIdUsuario())
                .build();
    }

    public TareaIdEntity tareaIdDtoToTareaIdEntity(TareaIdDTO tareaIdDto){
        return TareaIdEntity.builder()
                .idTarea(tareaIdDto.getIdTarea())
                .idUsuario(tareaIdDto.getIdUsuario())
                .build();
    }

}
