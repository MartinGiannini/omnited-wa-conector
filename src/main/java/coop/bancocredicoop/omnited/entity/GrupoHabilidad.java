package coop.bancocredicoop.omnited.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "grupo_habilidades")
public class GrupoHabilidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_habilidad")
    private Long idGrupoHabilidad;

    @Column(name = "grupo_habilidad_nombre")
    private String grupoHabilidadNombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sector", nullable = false)
    private Sector sector;

    @OneToMany(mappedBy = "grupoHabilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<GrupoHabilidadHabilidad> grupoHabilidadHabilidad;

    public GrupoHabilidad() {
    }

    public GrupoHabilidad(Long id, String nombre, Sector sector, Set<GrupoHabilidadHabilidad> habilidades) {
        this.idGrupoHabilidad = id;
        this.grupoHabilidadNombre = nombre;
        this.sector = sector;
        this.grupoHabilidadHabilidad = habilidades;
    }

    public Long getIdGrupoHabilidad() {
        return idGrupoHabilidad;
    }

    public void setIdGrupoHabilidad(Long id) {
        this.idGrupoHabilidad = id;
    }

    public String getGrupoHabilidadNombre() {
        return grupoHabilidadNombre;
    }

    public void setGrupoHabilidadNombre(String nombre) {
        this.grupoHabilidadNombre = nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Set<GrupoHabilidadHabilidad> getGrupoHabilidadHabilidad() {
        return grupoHabilidadHabilidad;
    }

    public void setGrupoHabilidadHabilidad(Set<GrupoHabilidadHabilidad> habilidades) {
        this.grupoHabilidadHabilidad = habilidades;
    }
    
    
}