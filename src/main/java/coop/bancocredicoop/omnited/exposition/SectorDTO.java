package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class SectorDTO {
    private Long idSector;
    private String sectorNombre;
    private DepartamentoDTO sectorDepartamento;
    private Set<EstadoDTO> sectorEstado;
    private Set<HabilidadDTO> sectorHabilidad;
    private Set<ColaDTO> sectorCola;
    private Set<UsuarioDTO> sectorUsuario;
    private Set<GrupoEstadoDTO> sectorGrupoEstado;
    private Set<GrupoHabilidadDTO> sectorGrupoHabilidad;

    public SectorDTO(Long id, String nombre, DepartamentoDTO departamento, Set<EstadoDTO> Estados, Set<HabilidadDTO> Habilidades, Set<ColaDTO> colas, Set<UsuarioDTO> usuarios, Set<GrupoEstadoDTO> grupoEstados, Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.idSector = id;
        this.sectorNombre = nombre;
        this.sectorDepartamento = departamento;
        this.sectorEstado = Estados;
        this.sectorHabilidad = Habilidades;
        this.sectorCola = colas;
        this.sectorUsuario = usuarios;
        this.sectorGrupoEstado = grupoEstados;
        this.sectorGrupoHabilidad = grupoHabilidades;
    }
    
    public SectorDTO() {}

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long id) {
        this.idSector = id;
    }

    public String getSectorNombre() {
        return sectorNombre;
    }

    public void setSectorNombre(String nombre) {
        this.sectorNombre = nombre;
    }

    public DepartamentoDTO getSectorDepartamento() {
        return sectorDepartamento;
    }

    public void setSectorDepartamento(DepartamentoDTO departamento) {
        this.sectorDepartamento = departamento;
    }
    
    public Set<EstadoDTO> getSectorEstado() {
        return sectorEstado;
    }

    public void setSectorEstado(Set<EstadoDTO> Estados) {
        this.sectorEstado = Estados;
    }

    public Set<HabilidadDTO> getSectorHabilidad() {
        return sectorHabilidad;
    }

    public void setSectorHabilidad(Set<HabilidadDTO> Habilidades) {
        this.sectorHabilidad = Habilidades;
    }

    public Set<ColaDTO> getSectorCola() {
        return sectorCola;
    }

    public void setSectorCola(Set<ColaDTO> colas) {
        this.sectorCola = colas;
    }

    public Set<UsuarioDTO> getSectorUsuario() {
        return sectorUsuario;
    }

    public void setSectorUsuario(Set<UsuarioDTO> usuarios) {
        this.sectorUsuario = usuarios;
    }

    public Set<GrupoEstadoDTO> getSectorGrupoEstado() {
        return sectorGrupoEstado;
    }

    public void setSectorGrupoEstado(Set<GrupoEstadoDTO> grupoEstados) {
        this.sectorGrupoEstado = grupoEstados;
    }

    public Set<GrupoHabilidadDTO> getSectorGrupoHabilidad() {
        return sectorGrupoHabilidad;
    }

    public void setSectorGrupoHabilidad(Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.sectorGrupoHabilidad = grupoHabilidades;
    }
}