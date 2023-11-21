package com.sura.gestiontareas.mapping;

import com.sura.gestiontareas.dto.UsuarioDTO;
import com.sura.gestiontareas.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapping {

    public UsuarioEntity usuarioDtoToUsuarioEntity(UsuarioDTO usuarioDto){
        return UsuarioEntity.builder()
                .idUsuario(usuarioDto.getIdUsuario())
                .email(usuarioDto.getEmail())
                .nombre(usuarioDto.getNombre())
                .build();
    }

    public UsuarioDTO usuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity){
        return UsuarioDTO.builder()
                .idUsuario(usuarioEntity.getIdUsuario())
                .email(usuarioEntity.getEmail())
                .nombre(usuarioEntity.getNombre())
                .build();
    }

    public List<UsuarioDTO> listusuarioEntityToUsuarioDto(List<UsuarioEntity> usuariosEntities){
        List<UsuarioDTO> usuariosDto = new ArrayList<>();
        usuariosEntities.forEach(usuarioEntity -> {
            UsuarioDTO usuarioDto = usuarioEntityToUsuarioDto(usuarioEntity);
            usuariosDto.add(usuarioDto);
        });
        return usuariosDto;
    }
}
