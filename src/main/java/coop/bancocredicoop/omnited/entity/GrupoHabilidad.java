package coop.bancocredicoop.omnited.entity;

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
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sector", nullable = false)
    private Sector sector;

    @OneToMany(mappedBy = "grupoHabilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoHabilidadHabilidad> habilidades;

    public GrupoHabilidad() {
    }

    public GrupoHabilidad(Long id, String nombre, Sector sector, Set<GrupoHabilidadHabilidad> habilidades) {
        this.idGrupoHabilidad = id;
        this.nombre = nombre;
        this.sector = sector;
        this.habilidades = habilidades;
    }

    public Long getId() {
        return idGrupoHabilidad;
    }

    public void setId(Long id) {
        this.idGrupoHabilidad = id;
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

    public Set<GrupoHabilidadHabilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<GrupoHabilidadHabilidad> habilidades) {
        this.habilidades = habilidades;
    }
    
    
}