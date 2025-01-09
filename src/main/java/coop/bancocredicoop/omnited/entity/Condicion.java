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
    private String nombre;

    @OneToMany(mappedBy = "condicion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarios;

    public Condicion() {
    }

    public Condicion(Long idCondicion, String nombre, Set<Usuario> usuarios) {
        this.idCondicion = idCondicion;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Long idCondicion) {
        this.idCondicion = idCondicion;
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