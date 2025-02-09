package coop.bancocredicoop.omnited.message.models;

public class RetornoCambiosRealizados {
    private String title;
    private String message;
    private String resultado;

    // Constructor por defecto
    public RetornoCambiosRealizados() {
    }

    // Constructor parametrizado
    public RetornoCambiosRealizados(String title, String message, String resultado) {
        this.title = title;
        this.message = message;
        this.resultado = resultado;
    }

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "RetornoCambiosRealizados{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}