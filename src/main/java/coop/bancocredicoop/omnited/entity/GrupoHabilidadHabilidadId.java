package coop.bancocredicoop.omnited.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GrupoHabilidadHabilidadId implements Serializable {

    @Column(name = "id_grupo_habilidad")
    private Long grupoHabilidadId;

    @Column(name = "id_habilidad")
    private Long habilidadId;

    // Constructor vacío
    public GrupoHabilidadHabilidadId() {
    }

    // Constructor con parámetros
    public GrupoHabilidadHabilidadId(Long grupoHabilidadId, Long habilidadId) {
        this.grupoHabilidadId = grupoHabilidadId;
        this.habilidadId = habilidadId;
    }

    // Getters y Setters
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

    // HashCode y Equals para clave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GrupoHabilidadHabilidadId that = (GrupoHabilidadHabilidadId) o;
        return Objects.equals(grupoHabilidadId, that.grupoHabilidadId)
                && Objects.equals(habilidadId, that.habilidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoHabilidadId, habilidadId);
    }
}
