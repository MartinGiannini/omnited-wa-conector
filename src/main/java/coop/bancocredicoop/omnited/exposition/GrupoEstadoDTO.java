package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class GrupoEstadoDTO {
    private Long id;
    private String nombre;
    private Set<EstadoDTO> estados;
    private SectorDTO sector;

    public GrupoEstadoDTO(Long id, String nombre, Set<EstadoDTO> estados, SectorDTO sector) {
        this.id = id;
        this.nombre = nombre;
        this.estados = estados;
        this.sector = sector;
    }

    public GrupoEstadoDTO() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<EstadoDTO> getEstados() {
        return estados;
    }

    public void setEstados(Set<EstadoDTO> estados) {
        this.estados = estados;
    }

    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
    }
}