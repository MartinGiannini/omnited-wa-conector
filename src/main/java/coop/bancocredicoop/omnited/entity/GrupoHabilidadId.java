package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrupoHabilidadId implements Serializable {

    /*
    private Long grupoHabilidadId;
    private Long habilidadId;

    // Getters y setters
    public Long getGrupoHabilidadId() {
        return grupoHabilidadId;
    }

    public void setGrupoHabilidadId(Long grupoHabilidadId) {
        this.grupoHabilidadId = grupoHabilidadId;
    }

    public Long getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Long habilidadId) {
        this.habilidadId = habilidadId;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoHabilidadId that = (GrupoHabilidadId) o;
        return Objects.equals(grupoHabilidadId, that.grupoHabilidadId) &&
               Objects.equals(habilidadId, that.habilidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoHabilidadId, habilidadId);
    }
*/
}