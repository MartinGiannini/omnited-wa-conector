package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long idPerfil;

    @Column(name = "perfil_nombre", length = 100, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarios;

    public Perfil() {
    }

    public Perfil(Long idPerfil, String nombre, Set<Usuario> usuarios) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
}