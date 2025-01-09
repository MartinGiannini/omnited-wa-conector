package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "grupo_estados_estados")
public class GrupoEstadoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_estado_estado")
    private Long idGrupoEstadoEstado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo_estado", nullable = false)
    private GrupoEstado grupoEstado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    public GrupoEstadoEstado() {
    }

    public GrupoEstadoEstado(Long idGrupoEstadoEstado, GrupoEstado grupoEstado, Estado estado) {
        this.idGrupoEstadoEstado = idGrupoEstadoEstado;
        this.grupoEstado = grupoEstado;
        this.estado = estado;
    }

    public Long getIdGrupoEstadoEstado() {
        return idGrupoEstadoEstado;
    }

    public void setIdGrupoEstadoEstado(Long idGrupoEstadoEstado) {
        this.idGrupoEstadoEstado = idGrupoEstadoEstado;
    }

    public GrupoEstado getGrupoEstado() {
        return grupoEstado;
    }

    public void setGrupoEstado(GrupoEstado grupoEstado) {
        this.grupoEstado = grupoEstado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}