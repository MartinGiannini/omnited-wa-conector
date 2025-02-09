package coop.bancocredicoop.omnited.exposition;

public class ExtensionDTO {
    private Long idExtension;
    private String extensionUri;
    private String extensionServer;
    private String extensionDominio;
    private String extensionUsername;
    private String extensionPassword;

    public ExtensionDTO() {
    }

    public ExtensionDTO(Long idExtension, String extensionUri, String extensionServer, String extensionDominio, String extensionUsername, String extensionPassword) {
        this.idExtension = idExtension;
        this.extensionUri = extensionUri;
        this.extensionServer = extensionServer;
        this.extensionDominio = extensionDominio;
        this.extensionUsername = extensionUsername;
        this.extensionPassword = extensionPassword;
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
}