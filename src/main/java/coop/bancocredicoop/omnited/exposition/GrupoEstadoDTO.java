package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class GrupoEstadoDTO {
    private Long idGrupoEstado;
    private String grupoEstadoNombre;
    private Set<EstadoDTO> estado;
    private SectorDTO sector;

    public GrupoEstadoDTO(Long id, String nombre, Set<EstadoDTO> estados, SectorDTO sector) {
        this.idGrupoEstado = id;
        this.grupoEstadoNombre = nombre;
        this.estado = estados;
        this.sector = sector;
    }

    public GrupoEstadoDTO() {
    }
    
    public Long getIdGrupoEstado() {
        return idGrupoEstado;
    }

    public void setIdGrupoEstado(Long id) {
        this.idGrupoEstado = id;
    }

    public String getGrupoEstadoNombre() {
        return grupoEstadoNombre;
    }

    public void setGrupoEstadoNombre(String nombre) {
        this.grupoEstadoNombre = nombre;
    }

    public Set<EstadoDTO> getEstado() {
        return estado;
    }

    public void setEstado(Set<EstadoDTO> estados) {
        this.estado = estados;
    }

    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
    }
}