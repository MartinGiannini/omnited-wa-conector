package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class SectorDTO {
    private Long id;
    private String nombre;
    private DepartamentoDTO departamento;
    private Set<EstadoDTO> Estados;
    private Set<HabilidadDTO> Habilidades;
    private Set<ColaDTO> colas;
    private Set<UsuarioDTO> usuarios;
    private Set<GrupoEstadoDTO> grupoEstados;
    private Set<GrupoHabilidadDTO> grupoHabilidades;

    public SectorDTO(Long id, String nombre, DepartamentoDTO departamento, Set<EstadoDTO> Estados, Set<HabilidadDTO> Habilidades, Set<ColaDTO> colas, Set<UsuarioDTO> usuarios, Set<GrupoEstadoDTO> grupoEstados, Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.Estados = Estados;
        this.Habilidades = Habilidades;
        this.colas = colas;
        this.usuarios = usuarios;
        this.grupoEstados = grupoEstados;
        this.grupoHabilidades = grupoHabilidades;
    }
    
    public SectorDTO() {}

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

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }
    
    public Set<EstadoDTO> getEstados() {
        return Estados;
    }

    public void setEstados(Set<EstadoDTO> Estados) {
        this.Estados = Estados;
    }

    public Set<HabilidadDTO> getHabilidades() {
        return Habilidades;
    }

    public void setHabilidades(Set<HabilidadDTO> Habilidades) {
        this.Habilidades = Habilidades;
    }

    public Set<ColaDTO> getColas() {
        return colas;
    }

    public void setColas(Set<ColaDTO> colas) {
        this.colas = colas;
    }

    public Set<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<GrupoEstadoDTO> getGrupoEstados() {
        return grupoEstados;
    }

    public void setGrupoEstados(Set<GrupoEstadoDTO> grupoEstados) {
        this.grupoEstados = grupoEstados;
    }

    public Set<GrupoHabilidadDTO> getGrupoHabilidades() {
        return grupoHabilidades;
    }

    public void setGrupoHabilidades(Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.grupoHabilidades = grupoHabilidades;
    }
}