package coop.bancocredicoop.omnited.exposition;

public class EstrategiaDTO {
    private Long idEstrategia;
    private String nombre;

    public EstrategiaDTO() {
    }

    public EstrategiaDTO(Long idEstrategia, String nombre) {
        this.idEstrategia = idEstrategia;
        this.nombre = nombre;
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
}