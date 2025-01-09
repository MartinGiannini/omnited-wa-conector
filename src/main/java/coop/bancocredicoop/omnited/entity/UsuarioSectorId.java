package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioSectorId implements Serializable {

    private Long usuarioId;
    private Long sectorId;

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioSectorId that = (UsuarioSectorId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(sectorId, that.sectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, sectorId);
    }
}