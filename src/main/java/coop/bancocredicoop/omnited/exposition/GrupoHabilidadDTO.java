package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class GrupoHabilidadDTO {
    private Long id;
    private String nombre;
    private Set<HabilidadDTO> habilidades;
    private SectorDTO sector;

    public GrupoHabilidadDTO(Long id, String nombre, Set<HabilidadDTO> habilidades, SectorDTO sector) {
        this.id = id;
        this.nombre = nombre;
        this.habilidades = habilidades;
        this.sector = sector;
    }
    
    public GrupoHabilidadDTO() {
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

    public Set<HabilidadDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<HabilidadDTO> habilidades) {
        this.habilidades = habilidades;
    }

    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
    }
}