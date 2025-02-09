package coop.bancocredicoop.omnited.exposition;

public class CondicionDTO {
    private Long idCondicion;
    private String condicionNombre;

    public CondicionDTO(Long id, String nombre) {
        this.idCondicion = id;
        this.condicionNombre = nombre;
    }

    public CondicionDTO() {}

    public Long getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Long id) {
        this.idCondicion = id;
    }

    public String getCondicionNombre() {
        return condicionNombre;
    }

    public void setCondicionNombre(String nombre) {
        this.condicionNombre = nombre;
    }
}