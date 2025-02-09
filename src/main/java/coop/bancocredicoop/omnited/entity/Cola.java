package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "colas")
@DynamicUpdate
public class Cola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cola")
    private Long idCola;

    @ManyToOne
    @JoinColumn(name = "id_estrategia", nullable = false)
    private Estrategia estrategia;

    @Column(name = "cola_nombre", length = 100, nullable = false)
    private String colaNombre;

    @Column(name = "cola_ringueo", nullable = false)
    private Long colaRingueo;

    @Column(name = "cola_espera", nullable = false)
    private Long colaEspera;

    @Column(name = "cola_autopausa", nullable = false)
    private Long colaAutoPausa;

    @Column(name = "cola_desborde")
    private Long colaDesborde;

    @Column(name = "cola_prioridad", nullable = false)
    private Long colaPrioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sector", nullable = false)
    private Sector sector; // Relación directa con Sector

    @OneToMany(mappedBy = "cola", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ColaHabilidad> colaHabilidad;

    public Cola() {
    }

    public Cola(Long idCola, Estrategia estrategia, String nombreCola, Long ringueo, Long espera, Long autoPausa, Long desborde, Long prioridad, Sector sector, Set<ColaHabilidad> habilidades) {
        this.idCola = idCola;
        this.estrategia = estrategia;
        this.colaNombre = nombreCola;
        this.colaRingueo = ringueo;
        this.colaEspera = espera;
        this.colaAutoPausa = autoPausa;
        this.colaDesborde = desborde;
        this.colaPrioridad = prioridad;
        this.sector = sector;
        this.colaHabilidad = habilidades;
    }

    public Long getIdCola() {
        return idCola;
    }

    public void setIdCola(Long idCola) {
        this.idCola = idCola;
    }

    public Estrategia getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }
    
    public String getColaNombre() {
        return colaNombre;
    }

    public void setColaNombre(String nombre) {
        this.colaNombre = nombre;
    }

    public Long getColaRingueo() {
        return colaRingueo;
    }

    public void setColaRingueo(Long ringueo) {
        this.colaRingueo = ringueo;
    }

    public Long getColaEspera() {
        return colaEspera;
    }

    public void setColaEspera(Long espera) {
        this.colaEspera = espera;
    }

    public Long getColaAutoPausa() {
        return colaAutoPausa;
    }

    public void setColaAutoPausa(Long autoPausa) {
        this.colaAutoPausa = autoPausa;
    }

    public Long getColaDesborde() {
        return colaDesborde;
    }

    public void setColaDesborde(Long desborde) {
        this.colaDesborde = desborde;
    }

    public Long getColaPrioridad() {
        return colaPrioridad;
    }

    public void setColaPrioridad(Long prioridad) {
        this.colaPrioridad = prioridad;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Set<ColaHabilidad> getColaHabilidad() {
        return colaHabilidad;
    }

    // Método para actualizar habilidades sin eliminar manualmente
    public void setColaHabilidad(Set<ColaHabilidad> nuevasHabilidades) {
        this.colaHabilidad.clear();  // Hibernate detecta los cambios y elimina las antiguas
        this.colaHabilidad.addAll(nuevasHabilidades);
    }
}