package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class ColaDTO {
    private Long idCola;
    private String colaNombre;
    private EstrategiaDTO colaEstrategia; // Estrategia asociada a la cola
    private Long colaRingueo;
    private Long colaEspera;
    private Long colaAutoPausa;
    private Long colaDesborde;
    private Long colaPrioridad;
    private Set<HabilidadDTO> colaHabilidad; // Habilidades asociadas a la cola
    private Long idSector;

    public ColaDTO() {
    }

    public ColaDTO(Long idCola, String colaNombre, EstrategiaDTO colaEstrategia, Long colaRingueo, Long colaEspera, Long colaAutoPausa, Long colaDesborde, Long colaPrioridad, Set<HabilidadDTO> colaHabilidad, Long idSector) {
        this.idCola = idCola;
        this.colaNombre = colaNombre;
        this.colaEstrategia = colaEstrategia;
        this.colaRingueo = colaRingueo;
        this.colaEspera = colaEspera;
        this.colaAutoPausa = colaAutoPausa;
        this.colaDesborde = colaDesborde;
        this.colaPrioridad = colaPrioridad;
        this.colaHabilidad = colaHabilidad;
        this.idSector = idSector;
    }

    public Long getIdCola() {
        return idCola;
    }

    public void setIdCola(Long id) {
        this.idCola = id;
    }

    public String getColaNombre() {
        return colaNombre;
    }

    public void setColaNombre(String nombre) {
        this.colaNombre = nombre;
    }

    public EstrategiaDTO getColaEstrategia() {
        return colaEstrategia;
    }

    public void setColaEstrategia(EstrategiaDTO estrategia) {
        this.colaEstrategia = estrategia;
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

    public Set<HabilidadDTO> getColaHabilidad() {
        return colaHabilidad;
    }

    public void setColaHabilidad(Set<HabilidadDTO> habilidades) {
        this.colaHabilidad = habilidades;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }
}