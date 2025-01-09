package coop.bancocredicoop.omnited.exposition;

public class EstadoDTO {
    private Long id;
    private String nombre;
    private boolean productivo;
    private boolean dedicadoUsuarioFinal;

    public EstadoDTO() {
    }

    // Constructor con argumentos
    public EstadoDTO(Long id, String nombre, boolean productivo, boolean dedicadoUsuarioFinal) {
        this.id = id;
        this.nombre = nombre;
        this.productivo = productivo;
        this.dedicadoUsuarioFinal = dedicadoUsuarioFinal;
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

    public boolean isProductivo() {
        return productivo;
    }

    public void setProductivo(boolean productivo) {
        this.productivo = productivo;
    }

    public boolean isDedicadoUsuarioFinal() {
        return dedicadoUsuarioFinal;
    }

    public void setDedicadoUsuarioFinal(boolean dedicadoUsuarioFinal) {
        this.dedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }

    
}