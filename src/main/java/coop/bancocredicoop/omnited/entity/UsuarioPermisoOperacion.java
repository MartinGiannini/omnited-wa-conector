package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_permisos_operacion")
public class UsuarioPermisoOperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_permisos_operacion_id_usuario_permiso_operacion_seq")
    @SequenceGenerator(name = "usuarios_permisos_operacion_id_usuario_permiso_operacion_seq", sequenceName = "usuarios_permisos_operacion_id_usuario_permiso_operacion_seq", allocationSize = 1)
    private Long idUsuarioPermisoOperacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso_operacion", nullable = false)
    private PermisoOperacion permiso;

    public UsuarioPermisoOperacion() {
    }

    public UsuarioPermisoOperacion(Long idUsuarioPermisoOperacion, Usuario usuario, PermisoOperacion permiso) {
        this.idUsuarioPermisoOperacion = idUsuarioPermisoOperacion;
        this.usuario = usuario;
        this.permiso = permiso;
    }

    public Long getIdUsuarioPermisoOperacion() {
        return idUsuarioPermisoOperacion;
    }

    public void setIdUsuarioPermisoOperacion(Long idUsuarioPermisoOperacion) {
        this.idUsuarioPermisoOperacion = idUsuarioPermisoOperacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PermisoOperacion getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoOperacion permiso) {
        this.permiso = permiso;
    }
}