package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "permisos_operacion")
public class PermisoOperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permisos_operacion_id_permiso_operacion_seq")
    @SequenceGenerator(name = "permisos_operacion_id_permiso_operacion_seq", sequenceName = "permisos_operacion_id_permiso_operacion_seq", allocationSize = 1)
    private Long idPermisoOperacion;

    @Column(name = "permiso_operacion_nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "permiso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoOperacion> usuarios;

    public PermisoOperacion() {
    }

    public PermisoOperacion(Long idPermisoOperacion, String nombre, Set<UsuarioPermisoOperacion> usuarios) {
        this.idPermisoOperacion = idPermisoOperacion;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getIdPermisoOperacion() {
        return idPermisoOperacion;
    }

    public void setIdPermisoOperacion(Long idPermisoOperacion) {
        this.idPermisoOperacion = idPermisoOperacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioPermisoOperacion> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioPermisoOperacion> usuarios) {
        this.usuarios = usuarios;
    }
}