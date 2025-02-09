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
    private String estrategiaNombre;

    @OneToMany(mappedBy = "estrategia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cola> cola;

    public Estrategia() {
    }

    public Estrategia(Long idEstrategia, String nombreEstrategia, Set<Cola> colas) {
        this.idEstrategia = idEstrategia;
        this.estrategiaNombre = nombreEstrategia;
        this.cola = colas;
    }

    public Long getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(Long idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getEstrategiaNombre() {
        return estrategiaNombre;
    }

    public void setEstrategiaNombre(String nombre) {
        this.estrategiaNombre = nombre;
    }

    public Set<Cola> getEstrategiaCola() {
        return cola;
    }

    public void setEstrategiaCola(Set<Cola> colas) {
        this.cola = colas;
    }

    
}