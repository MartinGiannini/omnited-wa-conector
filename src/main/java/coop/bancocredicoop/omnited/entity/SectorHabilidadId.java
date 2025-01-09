package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
//HECHO
@Embeddable
public class SectorHabilidadId implements Serializable {

    @Column(name = "id_sector")
    private Long sectorId;
    
    @Column(name = "id_habilidad")
    private Long habilidadId;

    // Getters y setters
    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
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
        SectorHabilidadId that = (SectorHabilidadId) o;
        return Objects.equals(sectorId, that.sectorId) &&
               Objects.equals(habilidadId, that.habilidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectorId, habilidadId);
    }
}