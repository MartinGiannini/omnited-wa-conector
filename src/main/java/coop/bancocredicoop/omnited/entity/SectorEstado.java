package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "sectores_estados")
public class SectorEstado {

    @EmbeddedId
    private SectorEstadoId id;

    @ManyToOne
    @MapsId("sectorId")
    @JoinColumn(name = "id_sector")
    private Sector sector;

    @ManyToOne
    @MapsId("estadoId")
    @JoinColumn(name = "id_estado")
    private Estado estado;

    public SectorEstado() {
    }

    public SectorEstado(SectorEstadoId id, Sector sector, Estado estado) {
        this.id = id;
        this.sector = sector;
        this.estado = estado;
    }

    public SectorEstadoId getId() {
        return id;
    }

    public void setId(SectorEstadoId id) {
        this.id = id;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}