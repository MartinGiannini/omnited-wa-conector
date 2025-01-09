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
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioSector> usuarios;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SectorEstado> estados;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SectorHabilidad> habilidades;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoEstado> gruposEstado;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrupoHabilidad> gruposHabilidad;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cola> colas; // Relación directa con Colas

    public Sector() {
    }

    public Sector(Long idSector, String nombre, Departamento departamento, Set<UsuarioSector> usuarios, Set<SectorEstado> estados, Set<SectorHabilidad> habilidades, Set<GrupoEstado> gruposEstado, Set<GrupoHabilidad> gruposHabilidad, Set<Cola> colas) {
        this.idSector = idSector;
        this.nombre = nombre;
        this.departamento = departamento;
        this.usuarios = usuarios;
        this.estados = estados;
        this.habilidades = habilidades;
        this.gruposEstado = gruposEstado;
        this.gruposHabilidad = gruposHabilidad;
        this.colas = colas;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<UsuarioSector> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioSector> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<SectorEstado> getEstados() {
        return estados;
    }

    public void setEstados(Set<SectorEstado> estados) {
        this.estados = estados;
    }

    public Set<SectorHabilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<SectorHabilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public Set<GrupoEstado> getGruposEstado() {
        return gruposEstado;
    }

    public void setGruposEstado(Set<GrupoEstado> gruposEstado) {
        this.gruposEstado = gruposEstado;
    }

    public Set<GrupoHabilidad> getGruposHabilidad() {
        return gruposHabilidad;
    }

    public void setGruposHabilidad(Set<GrupoHabilidad> gruposHabilidad) {
        this.gruposHabilidad = gruposHabilidad;
    }

    public Set<Cola> getColas() {
        return colas;
    }

    public void setColas(Set<Cola> colas) {
        this.colas = colas;
    }
    
    
}
