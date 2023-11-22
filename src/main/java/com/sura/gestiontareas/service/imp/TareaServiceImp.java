package com.sura.gestiontareas.service.imp;

import com.sura.gestiontareas.dto.TareaDTO;
import com.sura.gestiontareas.dto.TareaIdDTO;
import com.sura.gestiontareas.entity.TareaEntity;
import com.sura.gestiontareas.entity.UsuarioEntity;
import com.sura.gestiontareas.mapping.TareaMapping;
import com.sura.gestiontareas.mapping.UsuarioMapping;
import com.sura.gestiontareas.repository.iTareaRepository;
import com.sura.gestiontareas.repository.iUsuarioRepository;
import com.sura.gestiontareas.service.iTareaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TareaServiceImp implements iTareaService {

    private final iTareaRepository iTareaRepository;
    private final iUsuarioRepository iUsuarioRepository ;

    @Override
    public TareaDTO crearTarea(TareaDTO tarea) {
        Optional<UsuarioEntity> buscarUsuario = iUsuarioRepository.findById(tarea.getUsuarioDto().getIdUsuario());

        if (buscarUsuario.isPresent()) {
            tarea.setUsuarioDto(new UsuarioMapping().usuarioEntityToUsuarioDto(buscarUsuario.get()));
            TareaEntity tareaCreada = iTareaRepository.saveAndFlush(new TareaMapping().tareaDtoToTareaEntity(tarea));

            if (Objects.nonNull(tareaCreada)) {
                return new TareaMapping().tareaEntityToTareaDto(tareaCreada);
            }
        }
        return null;
    }

    @Override
    public TareaDTO buscarTarea(TareaIdDTO idTarea) {
        Optional<TareaEntity> tareaOptional = iTareaRepository.findById(
                new TareaMapping().tareaIdDtoToTareaIdEntity(idTarea));
        return tareaOptional.map(tareaEntity -> new TareaMapping().tareaEntityToTareaDto(tareaEntity))
                .orElse(null);
    }

    @Override
    public TareaDTO actualizarTarea(TareaDTO tareaDto) {
        Optional<UsuarioEntity> buscarUsuario = iUsuarioRepository.findById(tareaDto.getUsuarioDto().getIdUsuario());

        if (buscarUsuario.isPresent()) {
            tareaDto.setUsuarioDto(new UsuarioMapping().usuarioEntityToUsuarioDto(buscarUsuario.get()));
            TareaEntity tareaCreada = iTareaRepository.saveAndFlush(new TareaMapping().tareaDtoToTareaEntity(tareaDto));

            if (Objects.nonNull(tareaCreada)) {
                return new TareaMapping().tareaEntityToTareaDto(tareaCreada);
            }
        }
        return null;

    }

    @Override
    public String borrarTarea(TareaDTO tareaDto) {
        TareaDTO tarea = buscarTarea(tareaDto.getIdTareaDto());
        if(Objects.nonNull(tarea)){
            iTareaRepository.delete(new TareaMapping().tareaDtoToTareaEntity(tarea));
            return "Se borró la tarea satisfactoriamente";
        }
        return null;
    }

    @Override
    public List<TareaDTO> filtrarTareasPorFechaUsuario(LocalDate fechaInicio, LocalDate fechaFin, Integer idUsuario) {
        List<TareaEntity> tareas = iTareaRepository.findAll();

        if (fechaInicio != null) {
            tareas = tareas.stream()
                    .filter(t -> (t.getFechaInicio().isAfter(fechaInicio) || t.getFechaInicio().isEqual(fechaInicio)) && (t.getIdUsuario().getIdUsuario() == idUsuario))
                    .collect(Collectors.toList());
        }
        if (fechaFin != null) {
            tareas = tareas.stream()
                    .filter(t -> t.getFechaFin() == null || t.getFechaFin().isBefore(fechaFin) || t.getFechaFin().isEqual(fechaFin))
                    .collect(Collectors.toList());
        }

        // Convertir las entidades a DTO y devolver la lista resultante
        return tareas.stream()
                .map(t -> new TareaMapping().tareaEntityToTareaDto(t))
                .collect(Collectors.toList());
    }
}
