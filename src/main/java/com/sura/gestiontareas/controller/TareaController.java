package com.sura.gestiontareas.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.gestiontareas.dto.TareaDTO;
import com.sura.gestiontareas.dto.TareaIdDTO;
import com.sura.gestiontareas.service.iTareaService;
import com.sura.gestiontareas.util.LocalDateFomatter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/gestiontareas/tarea")
@CrossOrigin(origins = "*")
@Log4j2
public class TareaController {

    private final iTareaService iTareaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarTarea (@RequestBody TareaDTO tareaDto) throws Exception {
        try{
            TareaDTO tareaResponse = iTareaService.crearTarea(tareaDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(tareaResponse));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value ="/{idTarea}/{idUsuario}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TareaDTO> buscarTareaPorId( @PathVariable("idTarea") int idTarea,
                                                      @PathVariable("idUsuario") int idUsuario) {

        TareaIdDTO idTareaDto = TareaIdDTO.builder()
                .idTarea(idTarea)
                .idUsuario(idUsuario)
                .build();

        TareaDTO tareaDto = iTareaService.buscarTarea(idTareaDto);

        if (tareaDto != null) {
            return ResponseEntity.ok(tareaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/{idTarea}/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarTarea (
            @PathVariable("idTarea") int idTarea,
            @PathVariable("idUsuario") int idUsuario,
            @RequestBody TareaDTO tareaDto) throws JsonProcessingException {

        if (idTarea != tareaDto.getIdTareaDto().getIdTarea() ||
                idUsuario != tareaDto.getIdTareaDto().getIdUsuario()) {
            return ResponseEntity.badRequest().build();
        }

        LocalDate fechaInicio = new LocalDateFomatter().dateStringtoLocalDate(tareaDto.getFechaInicioDto());
        LocalDate fechaFin =  new LocalDateFomatter().dateStringtoLocalDate(tareaDto.getFechaFinDto());

        if (fechaFin.isBefore(fechaInicio)) {
            return ResponseEntity.badRequest().body("La fecha fin debe ser mayor o igual que la fecha inicio.");
        }

        TareaDTO tareaActualizada = iTareaService.actualizarTarea(tareaDto);
        if (tareaActualizada != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(tareaActualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/{idTarea}/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarTarea (
            @PathVariable("idTarea") int idTarea,
            @PathVariable("idUsuario") int idUsuario) throws JsonProcessingException {

        TareaIdDTO tareaDto = TareaIdDTO.builder()
                .idTarea(idTarea)
                .idUsuario(idUsuario)
                .build();

        String tareaResponse = iTareaService.borrarTarea(iTareaService.buscarTarea(tareaDto));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(tareaResponse));
        } catch (JsonProcessingException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/filtrar")
    public ResponseEntity<List<TareaDTO>> buscarTareaFiltro(
            @RequestParam("idUsuario") int idUsuario,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam(value = "fechaFin",required = false) String fechaFin){

        LocalDate fechaInicioParsed = new LocalDateFomatter().dateStringtoLocalDate(fechaInicio);
        LocalDate fechaFinParsed =  new LocalDateFomatter().dateStringtoLocalDate(fechaFin);

        List<TareaDTO> tareasFiltradas = iTareaService.filtrarTareasPorFechaUsuario(fechaInicioParsed, fechaFinParsed, idUsuario);

        return ResponseEntity.ok(tareasFiltradas);
    }
}

