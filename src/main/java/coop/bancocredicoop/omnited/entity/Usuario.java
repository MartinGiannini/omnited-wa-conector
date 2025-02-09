package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "usuarios")
@DynamicUpdate
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_usuario_seq")
    @SequenceGenerator(name = "usuarios_id_usuario_seq", sequenceName = "usuarios_id_usuario_seq", allocationSize = 1)
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    private Perfil usuarioPerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condicion")
    private Condicion usuarioCondicion;

    @Column(name = "usuario_nombre", nullable = false, length = 50)
    private String usuarioNombre;

    @Column(name = "usuario_apellido", nullable = false, length = 30)
    private String usuarioApellido;

    @Column(name = "usuario_usuario", nullable = false, length = 50)
    private String usuarioUsuario;

    @Column(name = "usuario_correo", nullable = false, length = 100)
    private String usuarioCorreo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_extension")
    private Extension usuarioExtension;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UsuarioHabilidad> usuarioHabilidad;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UsuarioEstado> usuarioEstado;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UsuarioPermisoAdministracion> usuarioPermisoAdministracion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UsuarioPermisoOperacion> usuarioPermisoOperacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UsuarioPermisoSupervision> usuarioPermisoSupervision;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UsuarioSector> usuarioSector;

    public Usuario() {
    }

    public Usuario(Long idUsuario, Perfil perfil, Condicion condicion, String nombre, String apellido, String usuario, String correo, Extension extension, Set<UsuarioHabilidad> usuarioHabilidades, Set<UsuarioEstado> usuarioEstados, Set<UsuarioPermisoAdministracion> permisosAdministracion, Set<UsuarioPermisoOperacion> permisosOperacion, Set<UsuarioPermisoSupervision> permisosSupervision, Set<UsuarioSector> usuarioSectores) {
        this.idUsuario = idUsuario;
        this.usuarioPerfil = perfil;
        this.usuarioCondicion = condicion;
        this.usuarioNombre = nombre;
        this.usuarioApellido = apellido;
        this.usuarioUsuario = usuario;
        this.usuarioCorreo = correo;
        this.usuarioExtension = extension;
        this.usuarioHabilidad = usuarioHabilidades;
        this.usuarioEstado = usuarioEstados;
        this.usuarioPermisoAdministracion = permisosAdministracion;
        this.usuarioPermisoOperacion = permisosOperacion;
        this.usuarioPermisoSupervision = permisosSupervision;
        this.usuarioSector = usuarioSectores;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Perfil getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(Perfil usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public Condicion getUsuarioCondicion() {
        return usuarioCondicion;
    }

    public void setUsuarioCondicion(Condicion usuarioCondicion) {
        this.usuarioCondicion = usuarioCondicion;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String nombre) {
        this.usuarioNombre = nombre;
    }

    public String getUsuarioApellido() {
        return usuarioApellido;
    }

    public void setUsuarioApellido(String apellido) {
        this.usuarioApellido = apellido;
    }

    public String getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(String usuario) {
        this.usuarioUsuario = usuario;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String correo) {
        this.usuarioCorreo = correo;
    }

    public Extension getUsuarioExtension() {
        return usuarioExtension;
    }

    public void setUsuarioExtension(Extension usuarioExtension) {
        this.usuarioExtension = usuarioExtension;
    }

    public Set<UsuarioHabilidad> getUsuarioHabilidad() {
        return usuarioHabilidad;
    }

    public void setUsuarioHabilidad(Set<UsuarioHabilidad> usuarioHabilidades) {
        this.usuarioHabilidad = usuarioHabilidades;
    }

    public Set<UsuarioEstado> getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(Set<UsuarioEstado> usuarioEstados) {
        this.usuarioEstado = usuarioEstados;
    }

    public Set<UsuarioPermisoAdministracion> getUsuarioPermisoAdministracion() {
        return usuarioPermisoAdministracion;
    }

    public void setUsuarioPermisoAdministracion(Set<UsuarioPermisoAdministracion> permisosAdministracion) {
        this.usuarioPermisoAdministracion = permisosAdministracion;
    }

    public Set<UsuarioPermisoOperacion> getUsuarioPermisoOperacion() {
        return usuarioPermisoOperacion;
    }

    public void setUsuarioPermisoOperacion(Set<UsuarioPermisoOperacion> permisosOperacion) {
        this.usuarioPermisoOperacion = permisosOperacion;
    }

    public Set<UsuarioPermisoSupervision> getUsuarioPermisoSupervision() {
        return usuarioPermisoSupervision;
    }

    public void setUsuarioPermisoSupervision(Set<UsuarioPermisoSupervision> permisosSupervision) {
        this.usuarioPermisoSupervision = permisosSupervision;
    }

    // Getters y Setters para usuarioSector
    public Set<UsuarioSector> getUsuarioSector() {
        return usuarioSector;
    }

    public void setUsuarioSector(Set<UsuarioSector> usuarioSectores) {
        this.usuarioSector = usuarioSectores;
    }
    
    public void setHabilidades(Set<UsuarioHabilidad> nuevasHabilidades) {
        this.usuarioHabilidad.clear();
        this.usuarioHabilidad.addAll(nuevasHabilidades);
    }

    public void setEstados(Set<UsuarioEstado> nuevosEstados) {
        this.usuarioEstado.clear();
        this.usuarioEstado.addAll(nuevosEstados);
    }

    public void setPermisosSupervision(Set<UsuarioPermisoSupervision> usuarioPermisoSupervision) {
        this.usuarioPermisoSupervision.clear();
        this.usuarioPermisoSupervision.addAll(usuarioPermisoSupervision);
    }
    
    public void setPermisosOperacion(Set<UsuarioPermisoOperacion> usuarioPermisoOperacion) {
        this.usuarioPermisoOperacion.clear();
        this.usuarioPermisoOperacion.addAll(usuarioPermisoOperacion);
    }
}