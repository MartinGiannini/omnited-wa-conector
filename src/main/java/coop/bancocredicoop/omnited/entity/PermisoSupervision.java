package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "permisos_supervision")
public class PermisoSupervision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permisos_supervision_id_permiso_supervision_seq")
    @SequenceGenerator(name = "permisos_supervision_id_permiso_supervision_seq", sequenceName = "permisos_supervision_id_permiso_supervision_seq", allocationSize = 1)
    private Long idPermisoSupervision;

    @Column(name = "permiso_supervision_nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "permiso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoSupervision> usuarios;

    public PermisoSupervision() {
    }

    public PermisoSupervision(Long idPermisoSupervision, String nombre, Set<UsuarioPermisoSupervision> usuarios) {
        this.idPermisoSupervision = idPermisoSupervision;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getIdPermisoSupervision() {
        return idPermisoSupervision;
    }

    public void setIdPermisoSupervision(Long idPermisoSupervision) {
        this.idPermisoSupervision = idPermisoSupervision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioPermisoSupervision> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioPermisoSupervision> usuarios) {
        this.usuarios = usuarios;
    }
}