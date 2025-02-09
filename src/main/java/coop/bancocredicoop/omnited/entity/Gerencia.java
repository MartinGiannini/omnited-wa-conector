package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "gerencias")
public class Gerencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerencia")
    private Long idGerencia;

    @Column(name = "gerencia_nombre", length = 50, nullable = false)
    private String gerenciaNombre;

    @OneToMany(mappedBy = "gerencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Departamento> departamento;

    public Gerencia() {
    }

    public Gerencia(Long idGerencia, String nombre, Set<Departamento> departamentos) {
        this.idGerencia = idGerencia;
        this.gerenciaNombre = nombre;
        this.departamento = departamentos;
    }

    public Long getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(Long idGerencia) {
        this.idGerencia = idGerencia;
    }

    public String getGerenciaNombre() {
        return gerenciaNombre;
    }

    public void setGerenciaNombre(String nombre) {
        this.gerenciaNombre = nombre;
    }

    public Set<Departamento> getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Set<Departamento> departamentos) {
        this.departamento = departamentos;
    }

   
}