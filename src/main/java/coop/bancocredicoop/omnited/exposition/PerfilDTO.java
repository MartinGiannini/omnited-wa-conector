package coop.bancocredicoop.omnited.exposition;

public class PerfilDTO {
    private Long id;
    private String nombre;

    public PerfilDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public PerfilDTO() {}

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
}