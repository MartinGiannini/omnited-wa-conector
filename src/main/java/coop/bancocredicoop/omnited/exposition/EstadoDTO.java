package coop.bancocredicoop.omnited.exposition;

public class EstadoDTO {
    private Long idEstado;
    private String estadoNombre;
    private boolean estadoProductivo;
    private boolean estadoDedicadoUsuarioFinal;

    public EstadoDTO() {
    }

    // Constructor con argumentos
    public EstadoDTO(Long id, String nombre, boolean productivo, boolean dedicadoUsuarioFinal) {
        this.idEstado = id;
        this.estadoNombre = nombre;
        this.estadoProductivo = productivo;
        this.estadoDedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }
    
    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long id) {
        this.idEstado = id;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String nombre) {
        this.estadoNombre = nombre;
    }

    public boolean isEstadoProductivo() {
        return estadoProductivo;
    }

    public void setEstadoProductivo(boolean productivo) {
        this.estadoProductivo = productivo;
    }

    public boolean isEstadoDedicadoUsuarioFinal() {
        return estadoDedicadoUsuarioFinal;
    }

    public void setEstadoDedicadoUsuarioFinal(boolean dedicadoUsuarioFinal) {
        this.estadoDedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }

    
}