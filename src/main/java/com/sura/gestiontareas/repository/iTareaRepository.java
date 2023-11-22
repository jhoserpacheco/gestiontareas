package com.sura.gestiontareas.repository;

import com.sura.gestiontareas.entity.TareaEntity;
import com.sura.gestiontareas.entity.TareaIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iTareaRepository extends JpaRepository<TareaEntity, TareaIdEntity> {
}
