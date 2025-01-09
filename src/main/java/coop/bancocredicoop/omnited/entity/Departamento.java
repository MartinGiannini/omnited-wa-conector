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
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_gerencia", nullable = false)
    private Gerencia gerencia;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sector> sectores;

    public Departamento() {
    }

    public Departamento(Long idDepartamento, String nombre, Gerencia gerencia, Set<Sector> sectores) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.gerencia = gerencia;
        this.sectores = sectores;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(Gerencia gerencia) {
        this.gerencia = gerencia;
    }

    public Set<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(Set<Sector> sectores) {
        this.sectores = sectores;
    }

    
}