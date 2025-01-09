package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrupoEstadoId implements Serializable {

    private Long grupoEstadoId;
    private Long estadoId;

    public GrupoEstadoId() {
    }

    public GrupoEstadoId(Long grupoEstadoId, Long estadoId) {
        this.grupoEstadoId = grupoEstadoId;
        this.estadoId = estadoId;
    }
    
    // Getters y setters
    public Long getGrupoEstadoId() {
        return grupoEstadoId;
    }

    public void setGrupoEstadoId(Long grupoEstadoId) {
        this.grupoEstadoId = grupoEstadoId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEstadoId that = (GrupoEstadoId) o;
        return Objects.equals(grupoEstadoId, that.grupoEstadoId) &&
               Objects.equals(estadoId, that.estadoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoEstadoId, estadoId);
    }
}