package coop.bancocredicoop.omnited.exposition;

public class PermisoDTO {
    private Long idPermiso;
    private String permisoNombre;

    public PermisoDTO(Long id, String nombre) {
        this.idPermiso = id;
        this.permisoNombre = nombre;
    }

    public PermisoDTO() {
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long id) {
        this.idPermiso = id;
    }

    public String getPermisoNombre() {
        return permisoNombre;
    }

    public void setPermisoNombre(String nombre) {
        this.permisoNombre = nombre;
    }
}