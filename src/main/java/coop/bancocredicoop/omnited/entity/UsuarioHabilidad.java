package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_habilidades")
public class UsuarioHabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_habilidades_id_usuario_habilidad_seq")
    @SequenceGenerator(name = "usuarios_habilidades_id_usuario_habilidad_seq", sequenceName = "usuarios_habilidades_id_usuario_habilidad_seq", allocationSize = 1)
    private Long idUsuarioHabilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidad", nullable = false)
    private Habilidad habilidad;

    @Column(name = "usuario_habilidad_valor", nullable = false)
    private Long usuarioHabilidadValor;

    public UsuarioHabilidad() {
    }

    public UsuarioHabilidad(Long idUsuarioHabilidad, Usuario usuario, Habilidad habilidad, Long valor) {
        this.idUsuarioHabilidad = idUsuarioHabilidad;
        this.usuario = usuario;
        this.habilidad = habilidad;
        this.usuarioHabilidadValor = valor;
    }

    public Long getIdUsuarioHabilidad() {
        return idUsuarioHabilidad;
    }

    public void setIdUsuarioHabilidad(Long idUsuarioHabilidad) {
        this.idUsuarioHabilidad = idUsuarioHabilidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Long getUsuarioHabilidadValor() {
        return usuarioHabilidadValor;
    }

    public void setUsuarioHabilidadValor(Long valor) {
        this.usuarioHabilidadValor = valor;
    }
    
    
}