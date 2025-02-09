package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class GrupoDatosDTO {
    private Set<GrupoEstadoDTO> grupoEstado;
    private Set<GrupoHabilidadDTO> grupoHabilidad;

    public GrupoDatosDTO() {}

    public GrupoDatosDTO(Set<GrupoEstadoDTO> grupoEstados, Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.grupoEstado = grupoEstados;
        this.grupoHabilidad = grupoHabilidades;
    }

    public Set<GrupoEstadoDTO> getGrupoEstado() {
        return grupoEstado;
    }

    public void setGrupoEstado(Set<GrupoEstadoDTO> grupoEstados) {
        this.grupoEstado = grupoEstados;
    }

    public Set<GrupoHabilidadDTO> getGrupoHabilidad() {
        return grupoHabilidad;
    }

    public void setGrupoHabilidad(Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.grupoHabilidad = grupoHabilidades;
    }
}