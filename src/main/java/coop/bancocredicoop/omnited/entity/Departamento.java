package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long idDepartamento;

    @Column(name = "departamento_nombre", length = 50, nullable = false)
    private String departamentoNombre;

    @ManyToOne
    @JoinColumn(name = "id_gerencia", nullable = false)
    private Gerencia gerencia;

    @OneToMany(mappedBy = "sectorDepartamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sector> sector;

    public Departamento() {
    }

    public Departamento(Long idDepartamento, String nombre, Gerencia gerencia, Set<Sector> sectores) {
        this.idDepartamento = idDepartamento;
        this.departamentoNombre = nombre;
        this.gerencia = gerencia;
        this.sector = sectores;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String nombre) {
        this.departamentoNombre = nombre;
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(Gerencia gerencia) {
        this.gerencia = gerencia;
    }

    public Set<Sector> getSector() {
        return sector;
    }

    public void setSector(Set<Sector> sectores) {
        this.sector = sectores;
    }

    
}