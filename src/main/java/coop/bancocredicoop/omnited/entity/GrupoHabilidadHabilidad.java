package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "grupo_habilidades_habilidades")
public class GrupoHabilidadHabilidad {

    @EmbeddedId
    private GrupoHabilidadId idGrupoHabilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("grupoHabilidadId")
    @JoinColumn(name = "id_grupo_habilidad")
    private GrupoHabilidad grupoHabilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("habilidadId")
    @JoinColumn(name = "id_habilidad")
    private Habilidad habilidad;
    
    @MapsId("grupoHabilidadValor")
    @JoinColumn(name = "grupo_habilidad_valor")
    private Long grupoHabilidadValor;
    
    public GrupoHabilidadHabilidad() {
    }

    public GrupoHabilidadHabilidad(GrupoHabilidadId idGrupoHabilidad, GrupoHabilidad grupoHabilidad, Habilidad habilidad, Long grupoHabilidadValor) {
        this.idGrupoHabilidad = idGrupoHabilidad;
        this.grupoHabilidad = grupoHabilidad;
        this.habilidad = habilidad;
        this.grupoHabilidadValor = grupoHabilidadValor;
    }

    public GrupoHabilidadId getId() {
        return idGrupoHabilidad;
    }

    public void setId(GrupoHabilidadId id) {
        this.idGrupoHabilidad = id;
    }

    public GrupoHabilidad getGrupoHabilidad() {
        return grupoHabilidad;
    }

    public void setGrupoHabilidad(GrupoHabilidad grupoHabilidad) {
        this.grupoHabilidad = grupoHabilidad;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Long getGrupoHabilidadValor() {
        return grupoHabilidadValor;
    }

    public void setGrupoHabilidadValor(Long grupoHabilidadValor) {
        this.grupoHabilidadValor = grupoHabilidadValor;
    }
}