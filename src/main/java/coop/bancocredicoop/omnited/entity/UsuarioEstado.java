package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_estados")
public class UsuarioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_estados_id_usuario_estado_seq")
    @SequenceGenerator(name = "usuarios_estados_id_usuario_estado_seq", sequenceName = "usuarios_estados_id_usuario_estado_seq", allocationSize = 1)
    private Long idUsuarioEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    public UsuarioEstado() {
    }

    public UsuarioEstado(Long idUsuarioEstado, Usuario usuario, Estado estado) {
        this.idUsuarioEstado = idUsuarioEstado;
        this.usuario = usuario;
        this.estado = estado;
    }

    public Long getIdUsuarioEstados() {
        return idUsuarioEstado;
    }

    public void setIdUsuarioEstados(Long idUsuarioEstado) {
        this.idUsuarioEstado = idUsuarioEstado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}