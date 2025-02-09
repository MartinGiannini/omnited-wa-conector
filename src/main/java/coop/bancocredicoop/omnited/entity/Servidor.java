package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "servidores")
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servidor")
    private Long idServidor;

    @Column(name = "servidor_nombre", length = 100, nullable = false)
    private String servidorNombre;

    @Column(name = "servidor_puerto", nullable = false)
    private Long servidorPuerto;

    @OneToMany(mappedBy = "extensionServidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Extension> extension;

    public Servidor() {
    }

    public Servidor(Long idServidor, String nombre, Long puerto, Set<Extension> extensiones) {
        this.idServidor = idServidor;
        this.servidorNombre = nombre;
        this.servidorPuerto = puerto;
        this.extension = extensiones;
    }

    public Long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Long idServidor) {
        this.idServidor = idServidor;
    }

    public String getServidorNombre() {
        return servidorNombre;
    }

    public void setServidorNombre(String nombre) {
        this.servidorNombre = nombre;
    }

    public Long getServidorPuerto() {
        return servidorPuerto;
    }

    public void setServidorPuerto(Long puerto) {
        this.servidorPuerto = puerto;
    }

    public Set<Extension> getExtension() {
        return extension;
    }

    public void setExtension(Set<Extension> extensiones) {
        this.extension = extensiones;
    }

    
}