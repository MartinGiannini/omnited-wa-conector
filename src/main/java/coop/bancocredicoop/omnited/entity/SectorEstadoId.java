package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SectorEstadoId implements Serializable {

    @Column(name = "id_sector")
    private Long sectorId;
    
    @Column(name = "id_habilidad")
    private Long estadoId;

    // Getters y setters
    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
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
        SectorEstadoId that = (SectorEstadoId) o;
        return Objects.equals(sectorId, that.sectorId) &&
               Objects.equals(estadoId, that.estadoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectorId, estadoId);
    }
}