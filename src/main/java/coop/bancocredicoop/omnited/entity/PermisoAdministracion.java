package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "permisos_administracion")
public class PermisoAdministracion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permisos_administracion_id_permiso_administracion_seq")
    @SequenceGenerator(name = "permisos_administracion_id_permiso_administracion_seq", sequenceName = "permisos_administracion_id_permiso_administracion_seq", allocationSize = 1)
    private Long idPermisoAdministracion;

    @Column(name = "permiso_administracion_nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "permiso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoAdministracion> usuarios;

    public PermisoAdministracion() {
    }

    public PermisoAdministracion(Long idPermisoAdministracion, String nombre, Set<UsuarioPermisoAdministracion> usuarios) {
        this.idPermisoAdministracion = idPermisoAdministracion;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getIdPermisoAdministracion() {
        return idPermisoAdministracion;
    }

    public void setIdPermisoAdministracion(Long idPermisoAdministracion) {
        this.idPermisoAdministracion = idPermisoAdministracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioPermisoAdministracion> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioPermisoAdministracion> usuarios) {
        this.usuarios = usuarios;
    }
}