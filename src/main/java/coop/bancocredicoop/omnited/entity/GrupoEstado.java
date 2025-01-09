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
    private String nombre;

    @OneToMany(mappedBy = "grupoEstado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoEstadoEstado> estados;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sector", nullable = false)
    private Sector sector;

    public GrupoEstado() {
    }

    public GrupoEstado(Long idGrupoEstado, String nombre, Set<GrupoEstadoEstado> estados, Sector sector) {
        this.idGrupoEstado = idGrupoEstado;
        this.nombre = nombre;
        this.estados = estados;
        this.sector = sector;
    }

    public Long getIdGrupoEstado() {
        return idGrupoEstado;
    }

    public void setIdGrupoEstado(Long idGrupoEstado) {
        this.idGrupoEstado = idGrupoEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Set<GrupoEstadoEstado> getEstados() {
        return estados;
    }

    public void setEstados(Set<GrupoEstadoEstado> estados) {
        this.estados = estados;
    }
}