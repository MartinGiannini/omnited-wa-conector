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
    private String nombre;

    @Column(name = "servidor_puerto", nullable = false)
    private Long puerto;

    @OneToMany(mappedBy = "servidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Extension> extensiones;

    public Servidor() {
    }

    public Servidor(Long idServidor, String nombre, Long puerto, Set<Extension> extensiones) {
        this.idServidor = idServidor;
        this.nombre = nombre;
        this.puerto = puerto;
        this.extensiones = extensiones;
    }

    public Long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Long idServidor) {
        this.idServidor = idServidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPuerto() {
        return puerto;
    }

    public void setPuerto(Long puerto) {
        this.puerto = puerto;
    }

    public Set<Extension> getExtensiones() {
        return extensiones;
    }

    public void setExtensiones(Set<Extension> extensiones) {
        this.extensiones = extensiones;
    }

    
}