package coop.bancocredicoop.omnited.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "extensiones")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_extension")
    private Long idExtension;

    @Column(name = "uri", length = 100, nullable = false)
    private String extensionUri;

    @Column(name = "server", length = 100, nullable = false)
    private String extensionServer;

    @Column(name = "dominio", length = 100)
    private String extensionDominio;

    @Column(name = "username", length = 100)
    private String extensionUsername;

    @Column(name = "password", length = 100)
    private String extensionPassword;

    @ManyToOne
    @JoinColumn(name = "id_servidor", nullable = false)
    private Servidor extensionServidor;
    
    @OneToMany(mappedBy = "usuarioExtension", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuario;

    public Extension() {
    }

    public Extension(Long idExtension, String extensionUri, String extensionServer, String extensionDominio, String extensionUsername, String extensionPassword, Servidor extensionServidor, Set<Usuario> usuario) {
        this.idExtension = idExtension;
        this.extensionUri = extensionUri;
        this.extensionServer = extensionServer;
        this.extensionDominio = extensionDominio;
        this.extensionUsername = extensionUsername;
        this.extensionPassword = extensionPassword;
        this.extensionServidor = extensionServidor;
        this.usuario = usuario;
    }

    public Long getIdExtension() {
        return idExtension;
    }

    public void setIdExtension(Long idExtension) {
        this.idExtension = idExtension;
    }

    public String getExtensionUri() {
        return extensionUri;
    }

    public void setExtensionUri(String extensionUri) {
        this.extensionUri = extensionUri;
    }

    public String getExtensionServer() {
        return extensionServer;
    }

    public void setExtensionServer(String extensionServer) {
        this.extensionServer = extensionServer;
    }

    public String getExtensionDominio() {
        return extensionDominio;
    }

    public void setExtensionDominio(String extensionDominio) {
        this.extensionDominio = extensionDominio;
    }

    public String getExtensionUsername() {
        return extensionUsername;
    }

    public void setExtensionUsername(String extensionUsername) {
        this.extensionUsername = extensionUsername;
    }

    public String getExtensionPassword() {
        return extensionPassword;
    }

    public void setExtensionPassword(String extensionPassword) {
        this.extensionPassword = extensionPassword;
    }

    public Servidor getExtensionServidor() {
        return extensionServidor;
    }

    public void setExtensionServidor(Servidor extensionServidor) {
        this.extensionServidor = extensionServidor;
    }

    public Set<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Set<Usuario> usuario) {
        this.usuario = usuario;
    }
}