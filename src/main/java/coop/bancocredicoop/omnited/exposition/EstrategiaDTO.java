package coop.bancocredicoop.omnited.exposition;

public class EstrategiaDTO {
    private Long idEstrategia;
    private String estrategiaNombre;

    public EstrategiaDTO(Long idEstrategia, String nombre) {
        this.idEstrategia = idEstrategia;
        this.estrategiaNombre = nombre;
    }

    public EstrategiaDTO() {
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
}