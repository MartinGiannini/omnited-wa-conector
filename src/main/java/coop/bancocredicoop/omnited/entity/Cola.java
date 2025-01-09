package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "colas")
public class Cola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cola")
    private Long idCola;

    @ManyToOne
    @JoinColumn(name = "id_estrategia", nullable = false)
    private Estrategia estrategia;

    @Column(name = "cola_nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "cola_ringueo", nullable = false)
    private Long ringueo;

    @Column(name = "cola_espera", nullable = false)
    private Long espera;

    @Column(name = "cola_autopausa", nullable = false)
    private Long autoPausa;

    @Column(name = "cola_desborde", length = 100)
    private String desborde;

    @Column(name = "cola_prioridad", nullable = false)
    private Short prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sector", nullable = false)
    private Sector sector; // Relación directa con Sector

    @OneToMany(mappedBy = "cola", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ColaHabilidad> habilidades;

    public Cola() {
    }

    public Cola(Long idCola, Estrategia estrategia, String nombre, Long ringueo, Long espera, Long autoPausa, String desborde, Short prioridad, Sector sector, Set<ColaHabilidad> habilidades) {
        this.idCola = idCola;
        this.estrategia = estrategia;
        this.nombre = nombre;
        this.ringueo = ringueo;
        this.espera = espera;
        this.autoPausa = autoPausa;
        this.desborde = desborde;
        this.prioridad = prioridad;
        this.sector = sector;
        this.habilidades = habilidades;
    }

    public Estrategia getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public Long getIdCola() {
        return idCola;
    }

    public void setIdCola(Long idCola) {
        this.idCola = idCola;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getRingueo() {
        return ringueo;
    }

    public void setRingueo(Long ringueo) {
        this.ringueo = ringueo;
    }

    public Long getEspera() {
        return espera;
    }

    public void setEspera(Long espera) {
        this.espera = espera;
    }

    public Long getAutoPausa() {
        return autoPausa;
    }

    public void setAutoPausa(Long autoPausa) {
        this.autoPausa = autoPausa;
    }

    public String getDesborde() {
        return desborde;
    }

    public void setDesborde(String desborde) {
        this.desborde = desborde;
    }

    public Short getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Short prioridad) {
        this.prioridad = prioridad;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Set<ColaHabilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<ColaHabilidad> habilidades) {
        this.habilidades = habilidades;
    }
}
