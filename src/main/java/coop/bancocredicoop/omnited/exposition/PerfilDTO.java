package coop.bancocredicoop.omnited.exposition;

public class PerfilDTO {
    private Long idPerfil;
    private String perfilNombre;

    public PerfilDTO(Long id, String nombre) {
        this.idPerfil = id;
        this.perfilNombre = nombre;
    }

    public PerfilDTO() {}

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long id) {
        this.idPerfil = id;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String nombre) {
        this.perfilNombre = nombre;
    }
}