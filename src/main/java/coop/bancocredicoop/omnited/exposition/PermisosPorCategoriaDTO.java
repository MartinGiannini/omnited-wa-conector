package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class PermisosPorCategoriaDTO {
    private Set<PermisoDTO> permisoAdministracion;
    private Set<PermisoDTO> permisoSupervision;
    private Set<PermisoDTO> permisoOperacion;

    public PermisosPorCategoriaDTO(Set<PermisoDTO> permisosAdministrador, Set<PermisoDTO> permisosSupervisor, Set<PermisoDTO> permisosOperador) {
        this.permisoAdministracion = permisosAdministrador;
        this.permisoSupervision = permisosSupervisor;
        this.permisoOperacion = permisosOperador;
    }

    // Getters y setters
    public Set<PermisoDTO> getPermisoAdministracion() {
        return permisoAdministracion;
    }

    public void setPermisoAdministracion(Set<PermisoDTO> permisosAdministracion) {
        this.permisoAdministracion = permisosAdministracion;
    }

    public Set<PermisoDTO> getPermisoSupervision() {
        return permisoSupervision;
    }

    public void setPermisoSupervision(Set<PermisoDTO> permisosSupervision) {
        this.permisoSupervision = permisosSupervision;
    }

    public Set<PermisoDTO> getPermisoOperacion() {
        return permisoOperacion;
    }

    public void setPermisoOperacion(Set<PermisoDTO> permisosOperacion) {
        this.permisoOperacion = permisosOperacion;
    }
}