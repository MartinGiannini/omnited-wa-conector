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
    private String nombre;

    @OneToMany(mappedBy = "gerencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Departamento> departamentos;

    public Gerencia() {
    }

    public Gerencia(Long idGerencia, String nombre, Set<Departamento> departamentos) {
        this.idGerencia = idGerencia;
        this.nombre = nombre;
        this.departamentos = departamentos;
    }

    public Long getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(Long idGerencia) {
        this.idGerencia = idGerencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

   
}