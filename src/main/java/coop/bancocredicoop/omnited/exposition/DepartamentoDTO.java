package coop.bancocredicoop.omnited.exposition;

public class DepartamentoDTO {
    private Long idDepartamento;
    private String departamentoNombre;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Long id, String nombre) {
        this.idDepartamento = id;
        this.departamentoNombre = nombre;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long id) {
        this.idDepartamento = id;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String nombre) {
        this.departamentoNombre = nombre;
    }
}