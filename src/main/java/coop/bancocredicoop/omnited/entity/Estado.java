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
    private String nombre;

    @Column(name = "estado_productivo", nullable = false)
    private Boolean productivo;

    @Column(name = "estado_dedicado_usuario_final", nullable = false)
    private Boolean dedicadoUsuarioFinal;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioEstado> usuarios;

    public Estado() {
    }

    public Estado(Long idEstado, String nombre, Boolean productivo, Boolean dedicadoUsuarioFinal, Set<UsuarioEstado> usuarios) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.productivo = productivo;
        this.dedicadoUsuarioFinal = dedicadoUsuarioFinal;
        this.usuarios = usuarios;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getProductivo() {
        return productivo;
    }

    public void setProductivo(Boolean productivo) {
        this.productivo = productivo;
    }

    public Boolean getDedicadoUsuarioFinal() {
        return dedicadoUsuarioFinal;
    }

    public void setDedicadoUsuarioFinal(Boolean dedicadoUsuarioFinal) {
        this.dedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }

    public Set<UsuarioEstado> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioEstado> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}