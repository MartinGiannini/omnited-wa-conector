package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_permisos_supervision")
public class UsuarioPermisoSupervision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_permisos_supervision_id_usuario_permiso_supervisio_seq")
    @SequenceGenerator(name = "usuarios_permisos_supervision_id_usuario_permiso_supervisio_seq", sequenceName = "usuarios_permisos_supervision_id_usuario_permiso_supervisio_seq", allocationSize = 1)
    private Long idUsuarioPermisoSupervision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso_supervision", nullable = false)
    private PermisoSupervision permisoSupervision;

    public UsuarioPermisoSupervision() {
    }

    public UsuarioPermisoSupervision(Long idUsuarioPermisoSupervision, Usuario usuario, PermisoSupervision permiso) {
        this.idUsuarioPermisoSupervision = idUsuarioPermisoSupervision;
        this.usuario = usuario;
        this.permisoSupervision = permiso;
    }

    public Long getIdUsuarioPermisoSupervision() {
        return idUsuarioPermisoSupervision;
    }

    public void setIdUsuarioPermisoSupervision(Long idUsuarioPermisoSupervision) {
        this.idUsuarioPermisoSupervision = idUsuarioPermisoSupervision;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PermisoSupervision getPermisoSupervision() {
        return permisoSupervision;
    }

    public void setPermisoSupervision(PermisoSupervision permiso) {
        this.permisoSupervision = permiso;
    }
}