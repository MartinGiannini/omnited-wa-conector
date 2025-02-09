package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;
// HECHO
@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estados_id_estado_seq")
    @SequenceGenerator(name = "estados_id_estado_seq", sequenceName = "estados_id_estado_seq", allocationSize = 1)
    private Long idEstado;

    @Column(name = "estado_nombre", length = 100)
    private String estadoNombre;

    @Column(name = "estado_productivo", nullable = false)
    private Boolean estadoProductivo;

    @Column(name = "estado_dedicado_usuario_final", nullable = false)
    private Boolean estadoDedicadoUsuarioFinal;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioEstado> usuarioEstado;

    public Estado() {
    }

    public Estado(Long idEstado, String nombre, Boolean productivo, Boolean dedicadoUsuarioFinal, Set<UsuarioEstado> usuarios) {
        this.idEstado = idEstado;
        this.estadoNombre = nombre;
        this.estadoProductivo = productivo;
        this.estadoDedicadoUsuarioFinal = dedicadoUsuarioFinal;
        this.usuarioEstado = usuarios;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String nombre) {
        this.estadoNombre = nombre;
    }

    public Boolean getEstadoProductivo() {
        return estadoProductivo;
    }

    public void setEstadoProductivo(Boolean productivo) {
        this.estadoProductivo = productivo;
    }

    public Boolean getEstadoDedicadoUsuarioFinal() {
        return estadoDedicadoUsuarioFinal;
    }

    public void setEstadoDedicadoUsuarioFinal(Boolean dedicadoUsuarioFinal) {
        this.estadoDedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }

    public Set<UsuarioEstado> getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(Set<UsuarioEstado> usuarios) {
        this.usuarioEstado = usuarios;
    }
    
    
}