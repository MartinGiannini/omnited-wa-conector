package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class GrupoDatosDTO {
    private Set<GrupoEstadoDTO> grupoEstados;
    private Set<GrupoHabilidadDTO> grupoHabilidades;

    public GrupoDatosDTO() {}

    public GrupoDatosDTO(Set<GrupoEstadoDTO> grupoEstados, Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.grupoEstados = grupoEstados;
        this.grupoHabilidades = grupoHabilidades;
    }

    public Set<GrupoEstadoDTO> getGrupoEstados() {
        return grupoEstados;
    }

    public void setGrupoEstados(Set<GrupoEstadoDTO> grupoEstados) {
        this.grupoEstados = grupoEstados;
    }

    public Set<GrupoHabilidadDTO> getGrupoHabilidades() {
        return grupoHabilidades;
    }

    public void setGrupoHabilidades(Set<GrupoHabilidadDTO> grupoHabilidades) {
        this.grupoHabilidades = grupoHabilidades;
    }
}