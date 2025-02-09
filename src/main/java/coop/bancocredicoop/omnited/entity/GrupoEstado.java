package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "grupo_estados")
public class GrupoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_estado")
    private Long idGrupoEstado;

    @Column(name = "grupo_estado_nombre")
    private String grupoEstadoNombre;

    @OneToMany(mappedBy = "grupoEstado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoEstadoEstado> grupoEstadoEstado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sector", nullable = false)
    private Sector sector;

    public GrupoEstado() {
    }

    public GrupoEstado(Long idGrupoEstado, String nombre, Set<GrupoEstadoEstado> estados, Sector sector) {
        this.idGrupoEstado = idGrupoEstado;
        this.grupoEstadoNombre = nombre;
        this.grupoEstadoEstado = estados;
        this.sector = sector;
    }

    public Long getIdGrupoEstado() {
        return idGrupoEstado;
    }

    public void setIdGrupoEstado(Long idGrupoEstado) {
        this.idGrupoEstado = idGrupoEstado;
    }

    public String getGrupoEstadoNombre() {
        return grupoEstadoNombre;
    }

    public void setGrupoEstadoNombre(String nombre) {
        this.grupoEstadoNombre = nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Set<GrupoEstadoEstado> getGrupoEstadoEstado() {
        return grupoEstadoEstado;
    }

    public void setGrupoEstadoEstado(Set<GrupoEstadoEstado> estados) {
        this.grupoEstadoEstado = estados;
    }
}