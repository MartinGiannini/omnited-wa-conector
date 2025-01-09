package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "extensiones")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_extension")
    private Long idExtension;

    @Column(name = "uri", length = 100, nullable = false)
    private String uri;

    @Column(name = "server", length = 100, nullable = false)
    private String server;

    @Column(name = "dominio", length = 100)
    private String dominio;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_servidor", nullable = false)
    private Servidor servidor;

    public Extension() {
    }

    public Extension(Long idExtension, String uri, String server, String dominio, String username, String password, Servidor servidor) {
        this.idExtension = idExtension;
        this.uri = uri;
        this.server = server;
        this.dominio = dominio;
        this.username = username;
        this.password = password;
        this.servidor = servidor;
    }

    public Long getIdExtension() {
        return idExtension;
    }

    public void setIdExtension(Long idExtension) {
        this.idExtension = idExtension;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    
}