package com.sura.gestiontareas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TareaIdEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private int idTarea;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TareaIdEntity entity = (TareaIdEntity) o;
        return Objects.equals(this.idTarea, entity.idTarea) &&
                Objects.equals(this.idUsuario, entity.idUsuario);
    }


    @Override
    public int hashCode() {
        return Objects.hash(idTarea, idUsuario);
    }
}
