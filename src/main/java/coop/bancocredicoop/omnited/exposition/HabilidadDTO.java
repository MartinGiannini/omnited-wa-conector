package coop.bancocredicoop.omnited.exposition;

public class HabilidadDTO {
    private Long id;
    private String nombre;
    private Long valor;

    public HabilidadDTO(Long id, String nombre, Long valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public HabilidadDTO() {}

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

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}