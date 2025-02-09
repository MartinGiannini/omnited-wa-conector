package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sectores")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sectores_id_sector_seq")
    @SequenceGenerator(name = "sectores_id_sector_seq", sequenceName = "sectores_id_sector_seq", allocationSize = 1)
    private Long idSector;

    @Column(name = "sector_nombre", nullable = false, length = 50)
    private String sectorNombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento sectorDepartamento;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioSector> usuarioSector;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SectorEstado> sectorEstado;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SectorHabilidad> sectorHabilidad;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoEstado> grupoEstado;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoHabilidad> grupoHabilidad;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cola> cola; // Relación directa con Colas

    public Sector() {
    }

    public Sector(Long idSector, String nombre, Departamento sectorDepartamento, Set<UsuarioSector> usuarios, Set<SectorEstado> estados, Set<SectorHabilidad> habilidades, Set<GrupoEstado> gruposEstado, Set<GrupoHabilidad> gruposHabilidad, Set<Cola> colas) {
        this.idSector = idSector;
        this.sectorNombre = nombre;
        this.sectorDepartamento = sectorDepartamento;
        this.usuarioSector = usuarios;
        this.sectorEstado = estados;
        this.sectorHabilidad = habilidades;
        this.grupoEstado = gruposEstado;
        this.grupoHabilidad = gruposHabilidad;
        this.cola = colas;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    public String getSectorNombre() {
        return sectorNombre;
    }

    public void setSectorNombre(String nombre) {
        this.sectorNombre = nombre;
    }

    public Departamento getSectorDepartamento() {
        return sectorDepartamento;
    }

    public void setSectorDepartamento(Departamento sectorDepartamento) {
        this.sectorDepartamento = sectorDepartamento;
    }

    public Set<UsuarioSector> getUsuarioSector() {
        return usuarioSector;
    }

    public void setUsuarioSector(Set<UsuarioSector> usuarios) {
        this.usuarioSector = usuarios;
    }

    public Set<SectorEstado> getSectorEstado() {
        return sectorEstado;
    }

    public void setSectorEstado(Set<SectorEstado> estados) {
        this.sectorEstado = estados;
    }

    public Set<SectorHabilidad> getSectorHabilidad() {
        return sectorHabilidad;
    }

    public void setSectorHabilidad(Set<SectorHabilidad> habilidades) {
        this.sectorHabilidad = habilidades;
    }

    public Set<GrupoEstado> getGrupoEstado() {
        return grupoEstado;
    }

    public void setGrupoEstado(Set<GrupoEstado> gruposEstado) {
        this.grupoEstado = gruposEstado;
    }

    public Set<GrupoHabilidad> getGrupoHabilidad() {
        return grupoHabilidad;
    }

    public void setGrupoHabilidad(Set<GrupoHabilidad> gruposHabilidad) {
        this.grupoHabilidad = gruposHabilidad;
    }

    public Set<Cola> getCola() {
        return cola;
    }

    public void setCola(Set<Cola> colas) {
        this.cola = colas;
    }
    
    
}
