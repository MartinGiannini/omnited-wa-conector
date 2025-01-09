package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;
// Hecho
@Entity
@Table(name = "habilidades")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habilidades_id_habilidad_seq")
    @SequenceGenerator(name = "habilidades_id_habilidad_seq", sequenceName = "habilidades_id_habilidad_seq", allocationSize = 1)
    private Long idHabilidad;

    @Column(name = "habilidad_nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioHabilidad> usuariosHabilidades;

    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ColaHabilidad> colasHabilidades;

    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoHabilidadHabilidad> grupoHabilidades;

    public Habilidad() {
    }

    public Habilidad(Long idHabilidad, String nombre, Set<UsuarioHabilidad> usuariosHabilidades, Set<ColaHabilidad> colasHabilidades, Set<GrupoHabilidadHabilidad> grupoHabilidades) {
        this.idHabilidad = idHabilidad;
        this.nombre = nombre;
        this.usuariosHabilidades = usuariosHabilidades;
        this.colasHabilidades = colasHabilidades;
        this.grupoHabilidades = grupoHabilidades;
    }

    public Long getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Long idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioHabilidad> getUsuariosHabilidades() {
        return usuariosHabilidades;
    }

    public void setUsuariosHabilidades(Set<UsuarioHabilidad> usuariosHabilidades) {
        this.usuariosHabilidades = usuariosHabilidades;
    }

    public Set<ColaHabilidad> getColasHabilidades() {
        return colasHabilidades;
    }

    public void setColasHabilidades(Set<ColaHabilidad> colasHabilidades) {
        this.colasHabilidades = colasHabilidades;
    }

    public Set<GrupoHabilidadHabilidad> getGrupoHabilidades() {
        return grupoHabilidades;
    }

    public void setGrupoHabilidades(Set<GrupoHabilidadHabilidad> grupoHabilidades) {
        this.grupoHabilidades = grupoHabilidades;
    }
    
    
}