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
    private String habilidadNombre;

    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioHabilidad> usuarioHabilidad;

    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ColaHabilidad> colaHabilidad;

    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoHabilidadHabilidad> grupoHabilidadHabilidad;

    public Habilidad() {
    }

    public Habilidad(Long idHabilidad, String nombre, Set<UsuarioHabilidad> usuariosHabilidades, Set<ColaHabilidad> colasHabilidades, Set<GrupoHabilidadHabilidad> grupoHabilidades) {
        this.idHabilidad = idHabilidad;
        this.habilidadNombre = nombre;
        this.usuarioHabilidad = usuariosHabilidades;
        this.colaHabilidad = colasHabilidades;
        this.grupoHabilidadHabilidad = grupoHabilidades;
    }

    public Long getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Long idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getHabilidadNombre() {
        return habilidadNombre;
    }

    public void setHabilidadNombre(String nombre) {
        this.habilidadNombre = nombre;
    }

    public Set<UsuarioHabilidad> getUsuarioHabilidad() {
        return usuarioHabilidad;
    }

    public void setUsuarioHabilidad(Set<UsuarioHabilidad> usuariosHabilidades) {
        this.usuarioHabilidad = usuariosHabilidades;
    }

    public Set<ColaHabilidad> getColaHabilidad() {
        return colaHabilidad;
    }

    public void setColaHabilidad(Set<ColaHabilidad> colasHabilidades) {
        this.colaHabilidad = colasHabilidades;
    }

    public Set<GrupoHabilidadHabilidad> getGrupoHabilidadHabilidad() {
        return grupoHabilidadHabilidad;
    }

    public void setGrupoHabilidadHabilidad(Set<GrupoHabilidadHabilidad> grupoHabilidades) {
        this.grupoHabilidadHabilidad = grupoHabilidades;
    }
    
    
}