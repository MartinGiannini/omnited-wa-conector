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
    private String PermisoSupervisionNombre;

    @OneToMany(mappedBy = "permisoSupervision", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoSupervision> usuarioPermisoSupervision;

    public PermisoSupervision() {
    }

    public PermisoSupervision(Long idPermisoSupervision, String nombre, Set<UsuarioPermisoSupervision> usuarios) {
        this.idPermisoSupervision = idPermisoSupervision;
        this.PermisoSupervisionNombre = nombre;
        this.usuarioPermisoSupervision = usuarios;
    }

    public Long getIdPermisoSupervision() {
        return idPermisoSupervision;
    }

    public void setIdPermisoSupervision(Long idPermisoSupervision) {
        this.idPermisoSupervision = idPermisoSupervision;
    }

    public String getPermisoSupervisionNombre() {
        return PermisoSupervisionNombre;
    }

    public void setPermisoSupervisionNombre(String nombre) {
        this.PermisoSupervisionNombre = nombre;
    }

    public Set<UsuarioPermisoSupervision> getUsuarioPermisoSupervision() {
        return usuarioPermisoSupervision;
    }

    public void setUsuarioPermisoSupervision(Set<UsuarioPermisoSupervision> usuarios) {
        this.usuarioPermisoSupervision = usuarios;
    }
}