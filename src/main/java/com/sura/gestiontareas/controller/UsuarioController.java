package com.sura.gestiontareas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.gestiontareas.dto.UsuarioDTO;
import com.sura.gestiontareas.service.iUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@ControllerAdvice
@RequestMapping("/gestiontareas/usuario")
@CrossOrigin(origins = "*")
@Log4j2
public class UsuarioController {

    private final iUsuarioService iUsuarioService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarUsuario (@RequestBody @Valid UsuarioDTO usuarioDto) {
        try {
            UsuarioDTO usuarioResponse = iUsuarioService.crearUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(usuarioResponse));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buscarUsuario(@Valid @PathVariable("idUsuario") Integer idUsuario ){
        UsuarioDTO usuarioResponse = iUsuarioService.buscarUsuario(idUsuario);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(usuarioResponse));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarUsuario(@RequestBody @Valid UsuarioDTO
                                                            usuarioDTO){
        try {
            UsuarioDTO usuarioResponse = iUsuarioService.actualizarUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(usuarioResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarUsuario(@Valid @PathVariable("idUsuario") Integer idUsuario){
        String usuarioResponse = iUsuarioService.
                borrarUsuario(iUsuarioService.buscarUsuario(idUsuario));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(usuarioResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping( value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> todosLosUsuarios(){
        try {
            List<UsuarioDTO> usuarioResponse = iUsuarioService.buscarTodosUsuarios();
            return ResponseEntity.ok(usuarioResponse);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
