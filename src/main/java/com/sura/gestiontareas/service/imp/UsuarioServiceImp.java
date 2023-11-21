package com.sura.gestiontareas.service.imp;

import com.sura.gestiontareas.dto.UsuarioDTO;
import com.sura.gestiontareas.entity.UsuarioEntity;
import com.sura.gestiontareas.mapping.UsuarioMapping;
import com.sura.gestiontareas.repository.iUsuarioRepository;
import com.sura.gestiontareas.service.iUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImp implements iUsuarioService {

    private final iUsuarioRepository iUsuarioRepository;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDto) {
        Optional<UsuarioEntity> buscarUsuario = iUsuarioRepository.findByIdUsuario(usuarioDto.getIdUsuario());
        if (buscarUsuario.isEmpty()){
            UsuarioEntity crearUsuario = iUsuarioRepository.
                    saveAndFlush(new UsuarioMapping().usuarioDtoToUsuarioEntity(usuarioDto));
            if(Objects.nonNull(crearUsuario)){
                return new UsuarioMapping().usuarioEntityToUsuarioDto(crearUsuario);
            }
        }
        return null;
    }

    @Override
    public UsuarioDTO buscarUsuario(int idUsuario) {
        Optional<UsuarioEntity> buscarUsuario = iUsuarioRepository.findById(idUsuario);
        if (buscarUsuario.isPresent()){
            UsuarioDTO usuarioDto = new UsuarioMapping().usuarioEntityToUsuarioDto(buscarUsuario.get());
            return usuarioDto;
        }
        return null;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDto) {
        Optional<UsuarioEntity> buscarElemento = iUsuarioRepository.findById(usuarioDto.getIdUsuario());
        if (buscarElemento.isPresent()) {
            UsuarioEntity actualizarElemento = iUsuarioRepository.saveAndFlush(new UsuarioMapping().
                    usuarioDtoToUsuarioEntity(usuarioDto));
            if(Objects.nonNull(actualizarElemento)){
                UsuarioDTO actualizarUsuarioDto = new UsuarioMapping().
                        usuarioEntityToUsuarioDto(actualizarElemento);
                return actualizarUsuarioDto;
            }
        }
        return null;
    }

    @Override
    public String borrarUsuario(UsuarioDTO usuarioDto) {
        UsuarioDTO buscarUsuarioDto = buscarUsuario(usuarioDto.getIdUsuario());
        if (Objects.nonNull(buscarUsuarioDto)){
            iUsuarioRepository.delete(new UsuarioMapping().usuarioDtoToUsuarioEntity(usuarioDto));
            return  "EL usuario fue borrado exitosamente";
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> buscarTodosUsuarios() {
        List<UsuarioEntity> UsuarioEntity = iUsuarioRepository.findAll();
        if (!UsuarioEntity.isEmpty()){
            List<UsuarioDTO> UsuarioDTOS = new UsuarioMapping().listusuarioEntityToUsuarioDto(UsuarioEntity);
            return UsuarioDTOS;
        }
        return null;
    }
}
