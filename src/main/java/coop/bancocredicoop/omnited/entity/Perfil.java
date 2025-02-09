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
    private String perfilNombre;

    @OneToMany(mappedBy = "usuarioPerfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuario;

    public Perfil() {
    }

    public Perfil(Long idPerfil, String nombre, Set<Usuario> usuarios) {
        this.idPerfil = idPerfil;
        this.perfilNombre = nombre;
        this.usuario = usuarios;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String nombre) {
        this.perfilNombre = nombre;
    }

    public Set<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Set<Usuario> usuarios) {
        this.usuario = usuarios;
    }

    
}