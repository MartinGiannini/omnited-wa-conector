package coop.bancocredicoop.omnited.message.models;

public class UsuarioDatos {
    
    private String usuario;

    // Getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "UsuarioDatos{" +
                "usuario='" + usuario + '\'' +
                '}';
    }
}