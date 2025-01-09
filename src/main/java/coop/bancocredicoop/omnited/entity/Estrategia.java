package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "estrategias")
public class Estrategia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estrategia")
    private Long idEstrategia;

    @Column(name = "estrategia_nombre", length = 100, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "estrategia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cola> colas;

    public Estrategia() {
    }

    public Estrategia(Long idEstrategia, String nombre, Set<Cola> colas) {
        this.idEstrategia = idEstrategia;
        this.nombre = nombre;
        this.colas = colas;
    }

    public Long getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(Long idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Cola> getColas() {
        return colas;
    }

    public void setColas(Set<Cola> colas) {
        this.colas = colas;
    }

    
}