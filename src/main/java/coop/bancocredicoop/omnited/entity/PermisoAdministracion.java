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
    private String nombrePermisoAdministracion;

    @OneToMany(mappedBy = "permisoAdministracion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoAdministracion> usuarioPermisoAdministracion;

    public PermisoAdministracion() {
    }

    public PermisoAdministracion(Long idPermisoAdministracion, String nombre, Set<UsuarioPermisoAdministracion> usuarios) {
        this.idPermisoAdministracion = idPermisoAdministracion;
        this.nombrePermisoAdministracion = nombre;
        this.usuarioPermisoAdministracion = usuarios;
    }

    public Long getIdPermisoAdministracion() {
        return idPermisoAdministracion;
    }

    public void setIdPermisoAdministracion(Long idPermisoAdministracion) {
        this.idPermisoAdministracion = idPermisoAdministracion;
    }

    public String getPermisoAdministracionNombre() {
        return nombrePermisoAdministracion;
    }

    public void setPermisoAdministracionNombre(String nombre) {
        this.nombrePermisoAdministracion = nombre;
    }

    public Set<UsuarioPermisoAdministracion> getUsuarioPermisoAdministracion() {
        return usuarioPermisoAdministracion;
    }

    public void setUsuarioPermisoAdministracion(Set<UsuarioPermisoAdministracion> usuarios) {
        this.usuarioPermisoAdministracion = usuarios;
    }
}