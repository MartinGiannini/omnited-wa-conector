package coop.bancocredicoop.omnited.exposition;

public class HabilidadDTO {
    private Long idHabilidad;
    private String habilidadNombre;
    private Long habilidadValor;

    public HabilidadDTO(Long idHabilidad, String nombreHabilidad, Long valorHabilidad) {
        this.idHabilidad = idHabilidad;
        this.habilidadNombre = nombreHabilidad;
        this.habilidadValor = valorHabilidad;
    }

    public HabilidadDTO() {}

    public Long getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Long id) {
        this.idHabilidad = id;
    }

    public String getHabilidadNombre() {
        return habilidadNombre;
    }

    public void setHabilidadNombre(String nombre) {
        this.habilidadNombre = nombre;
    }

    public Long getHabilidadValor() {
        return habilidadValor;
    }

    public void setHabilidadValor(Long valor) {
        this.habilidadValor = valor;
    }
}