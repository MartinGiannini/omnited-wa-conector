package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "colas_habilidades")
public class ColaHabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cola_habilidad")
    private Long idColaHabilidad;

    @ManyToOne
    @JoinColumn(name = "id_cola", nullable = false)
    private Cola cola;

    @ManyToOne
    @JoinColumn(name = "id_habilidad", nullable = false)
    private Habilidad habilidad;

    public ColaHabilidad() {
    }

    public ColaHabilidad(Long idColaHabilidad, Cola cola, Habilidad habilidad) {
        this.idColaHabilidad = idColaHabilidad;
        this.cola = cola;
        this.habilidad = habilidad;
    }

    public Long getIdColaHabilidad() {
        return idColaHabilidad;
    }

    public void setIdColaHabilidad(Long idColaHabilidad) {
        this.idColaHabilidad = idColaHabilidad;
    }

    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    
}