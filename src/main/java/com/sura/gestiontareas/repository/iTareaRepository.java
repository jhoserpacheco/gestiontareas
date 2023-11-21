package com.sura.gestiontareas.repository;

import com.sura.gestiontareas.entity.TareaEntity;
import com.sura.gestiontareas.entity.TareaIdEntity;
import com.sura.gestiontareas.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iTareaRepository extends JpaRepository<TareaEntity, TareaIdEntity> {
}
