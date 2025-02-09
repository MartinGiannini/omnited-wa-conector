package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "condiciones")
public class Condicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condicion")
    private Long idCondicion;

    @Column(name = "condicion_nombre", length = 50, nullable = false)
    private String condicionNombre;

    @OneToMany(mappedBy = "usuarioCondicion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuario;

    public Condicion() {
    }

    public Condicion(Long idCondicion, String nombre, Set<Usuario> usuarios) {
        this.idCondicion = idCondicion;
        this.condicionNombre = nombre;
        this.usuario = usuarios;
    }

    public Long getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Long idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getCondicionNombre() {
        return condicionNombre;
    }

    public void setCondicionNombre(String nombre) {
        this.condicionNombre = nombre;
    }

    public Set<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Set<Usuario> usuarios) {
        this.usuario = usuarios;
    }

    
}