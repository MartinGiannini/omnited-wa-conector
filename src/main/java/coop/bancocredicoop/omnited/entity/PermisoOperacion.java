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
    private String PermisoOperacionNombre;

    @OneToMany(mappedBy = "permisoOperacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoOperacion> usuarioPermisoOperacion;

    public PermisoOperacion() {
    }

    public PermisoOperacion(Long idPermisoOperacion, String nombre, Set<UsuarioPermisoOperacion> usuarios) {
        this.idPermisoOperacion = idPermisoOperacion;
        this.PermisoOperacionNombre = nombre;
        this.usuarioPermisoOperacion = usuarios;
    }

    public Long getIdPermisoOperacion() {
        return idPermisoOperacion;
    }

    public void setIdPermisoOperacion(Long idPermisoOperacion) {
        this.idPermisoOperacion = idPermisoOperacion;
    }

    public String getPermisoOperacionNombre() {
        return PermisoOperacionNombre;
    }

    public void setPermisoOperacionNombre(String nombre) {
        this.PermisoOperacionNombre = nombre;
    }

    public Set<UsuarioPermisoOperacion> getUsuarioPermisoOperacion() {
        return usuarioPermisoOperacion;
    }

    public void setUsuarioPermisoOperacion(Set<UsuarioPermisoOperacion> usuarios) {
        this.usuarioPermisoOperacion = usuarios;
    }
}