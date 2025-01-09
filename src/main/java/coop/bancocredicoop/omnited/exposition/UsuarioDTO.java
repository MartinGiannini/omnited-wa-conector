package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String usuario;
    private String correo;
    private PerfilDTO perfil;
    private CondicionDTO condicion;
    private Set<HabilidadDTO> habilidades;
    private Set<EstadoDTO> estados;
    private Set<SectorDTO> sectores;
    private Set<PermisoDTO> permisosAdministracion; // Agrupación de permisos
    private Set<PermisoDTO> permisosSupervision; // Agrupación de permisos
    private Set<PermisoDTO> permisosOperacion; // Agrupación de permisos

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String nombre, String apellido, String usuario, String correo, PerfilDTO perfil, CondicionDTO condicion, Set<HabilidadDTO> habilidades, Set<EstadoDTO> estados, Set<SectorDTO> sectores, Set<PermisoDTO> permisosAdministracion, Set<PermisoDTO> permisosSupervision, Set<PermisoDTO> permisosOperacion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.correo = correo;
        this.perfil = perfil;
        this.condicion = condicion;
        this.habilidades = habilidades;
        this.estados = estados;
        this.sectores = sectores;
        this.permisosAdministracion = permisosAdministracion;
        this.permisosSupervision = permisosSupervision;
        this.permisosOperacion = permisosOperacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDTO perfil) {
        this.perfil = perfil;
    }

    public CondicionDTO getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionDTO condicion) {
        this.condicion = condicion;
    }

    public Set<HabilidadDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<HabilidadDTO> habilidades) {
        this.habilidades = habilidades;
    }

    public Set<EstadoDTO> getEstados() {
        return estados;
    }

    public void setEstados(Set<EstadoDTO> estados) {
        this.estados = estados;
    }

    public Set<SectorDTO> getSectores() {
        return sectores;
    }

    public void setSectores(Set<SectorDTO> sectores) {
        this.sectores = sectores;
    }

    public Set<PermisoDTO> getPermisosAdministracion() {
        return permisosAdministracion;
    }

    public void setPermisosAdministracion(Set<PermisoDTO> permisosAdministracion) {
        this.permisosAdministracion = permisosAdministracion;
    }

    public Set<PermisoDTO> getPermisosSupervision() {
        return permisosSupervision;
    }

    public void setPermisosSupervision(Set<PermisoDTO> permisosSupervision) {
        this.permisosSupervision = permisosSupervision;
    }

    public Set<PermisoDTO> getPermisosOperacion() {
        return permisosOperacion;
    }

    public void setPermisosOperacion(Set<PermisoDTO> permisosOperacion) {
        this.permisosOperacion = permisosOperacion;
    }
}