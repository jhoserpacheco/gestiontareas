package com.sura.gestiontareas.service;

import com.sura.gestiontareas.dto.TareaDTO;
import com.sura.gestiontareas.dto.TareaIdDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface iTareaService {
    TareaDTO crearTarea(TareaDTO tareaDto);
    TareaDTO buscarTarea(TareaIdDTO idTarea);
    TareaDTO actualizarTarea(TareaDTO tareaDto);
    String borrarTarea(TareaDTO tareaDto);
    List<TareaDTO> buscarTodosTarea();
}
