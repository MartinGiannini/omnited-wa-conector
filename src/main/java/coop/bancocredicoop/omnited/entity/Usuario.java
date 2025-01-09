package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
//HECHO
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_usuario_seq")
    @SequenceGenerator(name = "usuarios_id_usuario_seq", sequenceName = "usuarios_id_usuario_seq", allocationSize = 1)
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condicion")
    private Condicion condicion;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String usuario;

    @Column(nullable = false, length = 100)
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_extension")
    private Extension extension;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioHabilidad> usuarioHabilidades;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioEstado> usuarioEstados;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoAdministracion> permisosAdministracion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoOperacion> permisosOperacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermisoSupervision> permisosSupervision;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioSector> usuarioSectores;

    public Usuario() {
    }

    public Usuario(Long idUsuario, Perfil perfil, Condicion condicion, String nombre, String apellido, String usuario, String correo, Extension extension, Set<UsuarioHabilidad> usuarioHabilidades, Set<UsuarioEstado> usuarioEstados, Set<UsuarioPermisoAdministracion> permisosAdministracion, Set<UsuarioPermisoOperacion> permisosOperacion, Set<UsuarioPermisoSupervision> permisosSupervision, Set<UsuarioSector> usuarioSectores) {
        this.idUsuario = idUsuario;
        this.perfil = perfil;
        this.condicion = condicion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.correo = correo;
        this.extension = extension;
        this.usuarioHabilidades = usuarioHabilidades;
        this.usuarioEstados = usuarioEstados;
        this.permisosAdministracion = permisosAdministracion;
        this.permisosOperacion = permisosOperacion;
        this.permisosSupervision = permisosSupervision;
        this.usuarioSectores = usuarioSectores;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public Set<UsuarioHabilidad> getUsuarioHabilidades() {
        return usuarioHabilidades;
    }

    public void setUsuarioHabilidades(Set<UsuarioHabilidad> usuarioHabilidades) {
        this.usuarioHabilidades = usuarioHabilidades;
    }

    public Set<UsuarioEstado> getUsuarioEstados() {
        return usuarioEstados;
    }

    public void setUsuarioEstados(Set<UsuarioEstado> usuarioEstados) {
        this.usuarioEstados = usuarioEstados;
    }

    public Set<UsuarioPermisoAdministracion> getPermisosAdministracion() {
        return permisosAdministracion;
    }

    public void setPermisosAdministracion(Set<UsuarioPermisoAdministracion> permisosAdministracion) {
        this.permisosAdministracion = permisosAdministracion;
    }

    public Set<UsuarioPermisoOperacion> getPermisosOperacion() {
        return permisosOperacion;
    }

    public void setPermisosOperacion(Set<UsuarioPermisoOperacion> permisosOperacion) {
        this.permisosOperacion = permisosOperacion;
    }

    public Set<UsuarioPermisoSupervision> getPermisosSupervision() {
        return permisosSupervision;
    }

    public void setPermisosSupervision(Set<UsuarioPermisoSupervision> permisosSupervision) {
        this.permisosSupervision = permisosSupervision;
    }

    public Set<Sector> getSectores() {
        return usuarioSectores.stream()
                              .map(UsuarioSector::getSector)
                              .collect(Collectors.toSet());
    }

    // Getters y Setters para usuarioSectores
    public Set<UsuarioSector> getUsuarioSectores() {
        return usuarioSectores;
    }

    public void setUsuarioSectores(Set<UsuarioSector> usuarioSectores) {
        this.usuarioSectores = usuarioSectores;
    }
}