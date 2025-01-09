package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "sectores_habilidades")
public class SectorHabilidad {

    @EmbeddedId
    private SectorHabilidadId id;

    @ManyToOne
    @MapsId("sectorId")
    @JoinColumn(name = "id_sector")
    private Sector sector;

    @ManyToOne
    @MapsId("habilidadId")
    @JoinColumn(name = "id_habilidad")
    private Habilidad habilidad;

    public SectorHabilidad() {
    }

    public SectorHabilidad(SectorHabilidadId id, Sector sector, Habilidad habilidad) {
        this.id = id;
        this.sector = sector;
        this.habilidad = habilidad;
    }

    public SectorHabilidadId getId() {
        return id;
    }

    public void setId(SectorHabilidadId id) {
        this.id = id;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }
    
    
}