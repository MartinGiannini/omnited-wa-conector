package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class UsuarioDTO {
    private Long idUsuario;
    private String usuarioNombre;
    private String usuarioApellido;
    private String usuarioUsuario;
    private String usuarioCorreo;
    private ExtensionDTO usuarioExtension;
    private PerfilDTO usuarioPerfil;
    private CondicionDTO usuarioCondicion;
    private Set<HabilidadDTO> usuarioHabilidad;
    private Set<EstadoDTO> usuarioEstado;
    private Set<SectorDTO> usuarioSector;
    private Set<PermisoDTO> usuarioPermisoAdministracion; // Agrupación de permisos
    private Set<PermisoDTO> usuarioPermisoSupervision; // Agrupación de permisos
    private Set<PermisoDTO> usuarioPermisoOperacion; // Agrupación de permisos

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String usuarioNombre, String usuarioApellido, String usuarioUsuario, String usuarioCorreo, ExtensionDTO usuarioExtension, PerfilDTO usuarioPerfil, CondicionDTO usuarioCondicion, Set<HabilidadDTO> usuarioHabilidad, Set<EstadoDTO> usuarioEstado, Set<SectorDTO> usuarioSector, Set<PermisoDTO> usuarioPermisoAdministracion, Set<PermisoDTO> usuarioPermisoSupervision, Set<PermisoDTO> usuarioPermisoOperacion) {
        this.idUsuario = idUsuario;
        this.usuarioNombre = usuarioNombre;
        this.usuarioApellido = usuarioApellido;
        this.usuarioUsuario = usuarioUsuario;
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioExtension = usuarioExtension;
        this.usuarioPerfil = usuarioPerfil;
        this.usuarioCondicion = usuarioCondicion;
        this.usuarioHabilidad = usuarioHabilidad;
        this.usuarioEstado = usuarioEstado;
        this.usuarioSector = usuarioSector;
        this.usuarioPermisoAdministracion = usuarioPermisoAdministracion;
        this.usuarioPermisoSupervision = usuarioPermisoSupervision;
        this.usuarioPermisoOperacion = usuarioPermisoOperacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public ExtensionDTO getUsuarioExtension() {
        return usuarioExtension;
    }

    public void setUsuarioExtension(ExtensionDTO usuarioExtension) {
        this.usuarioExtension = usuarioExtension;
    }
    
    public PerfilDTO getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(PerfilDTO perfil) {
        this.usuarioPerfil = perfil;
    }

    public CondicionDTO getUsuarioCondicion() {
        return usuarioCondicion;
    }

    public void setUsuarioCondicion(CondicionDTO condicion) {
        this.usuarioCondicion = condicion;
    }

    public Set<HabilidadDTO> getUsuarioHabilidad() {
        return usuarioHabilidad;
    }

    public void setUsuarioHabilidad(Set<HabilidadDTO> habilidades) {
        this.usuarioHabilidad = habilidades;
    }

    public Set<EstadoDTO> getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(Set<EstadoDTO> estados) {
        this.usuarioEstado = estados;
    }

    public Set<SectorDTO> getUsuarioSector() {
        return usuarioSector;
    }

    public void setUsuarioSector(Set<SectorDTO> sectores) {
        this.usuarioSector = sectores;
    }

    public Set<PermisoDTO> getUsuarioPermisoAdministracion() {
        return usuarioPermisoAdministracion;
    }

    public void setUsuarioPermisoAdministracion(Set<PermisoDTO> permisosAdministracion) {
        this.usuarioPermisoAdministracion = permisosAdministracion;
    }

    public Set<PermisoDTO> getUsuarioPermisoSupervision() {
        return usuarioPermisoSupervision;
    }

    public void setUsuarioPermisoSupervision(Set<PermisoDTO> permisosSupervision) {
        this.usuarioPermisoSupervision = permisosSupervision;
    }

    public Set<PermisoDTO> getUsuarioPermisoOperacion() {
        return usuarioPermisoOperacion;
    }

    public void setUsuarioPermisoOperacion(Set<PermisoDTO> permisosOperacion) {
        this.usuarioPermisoOperacion = permisosOperacion;
    }
}