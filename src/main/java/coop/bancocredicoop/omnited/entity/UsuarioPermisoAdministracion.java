package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_permisos_administracion")
public class UsuarioPermisoAdministracion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_permisos_administrac_id_usuario_permiso_administra_seq")
    @SequenceGenerator(name = "usuarios_permisos_administrac_id_usuario_permiso_administra_seq", sequenceName = "usuarios_permisos_administrac_id_usuario_permiso_administra_seq", allocationSize = 1)
    private Long idUsuarioPermisoAdministracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso_administracion", nullable = false)
    private PermisoAdministracion permiso;

    public UsuarioPermisoAdministracion() {
    }

    public UsuarioPermisoAdministracion(Long idUsuarioPermisoAdministracion, Usuario usuario, PermisoAdministracion permiso) {
        this.idUsuarioPermisoAdministracion = idUsuarioPermisoAdministracion;
        this.usuario = usuario;
        this.permiso = permiso;
    }

    public Long getIdUsuarioPermisoAdministracion() {
        return idUsuarioPermisoAdministracion;
    }

    public void setIdUsuarioPermisoAdministracion(Long idUsuarioPermisoAdministracion) {
        this.idUsuarioPermisoAdministracion = idUsuarioPermisoAdministracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PermisoAdministracion getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoAdministracion permiso) {
        this.permiso = permiso;
    }
}