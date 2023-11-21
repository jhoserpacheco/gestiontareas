package com.sura.gestiontareas.service;

import com.sura.gestiontareas.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface iUsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDto);

    UsuarioDTO buscarUsuario(int idUsuario);

    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDto);
    String borrarUsuario(UsuarioDTO usuarioDto);
    List<UsuarioDTO> buscarTodosUsuarios();
}
