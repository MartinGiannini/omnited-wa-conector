package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class ColaDTO {
    private Long id;
    private String nombre;
    private EstrategiaDTO estrategia; // Estrategia asociada a la cola
    private Long ringueo;
    private Long espera;
    private Long autoPausa;
    private String desborde;
    private Short prioridad;
    private Set<HabilidadDTO> habilidades; // Habilidades asociadas a la cola

    public ColaDTO() {
    }

    public ColaDTO(Long id, String nombre, EstrategiaDTO estrategia, Long ringueo, Long espera, Long autoPausa, String desborde, Short prioridad, Set<HabilidadDTO> habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.estrategia = estrategia;
        this.ringueo = ringueo;
        this.espera = espera;
        this.autoPausa = autoPausa;
        this.desborde = desborde;
        this.prioridad = prioridad;
        this.habilidades = habilidades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstrategiaDTO getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(EstrategiaDTO estrategia) {
        this.estrategia = estrategia;
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

    public Set<HabilidadDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<HabilidadDTO> habilidades) {
        this.habilidades = habilidades;
    }
}