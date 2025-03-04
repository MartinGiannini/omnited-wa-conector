package coop.bancocredicoop.omnited.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_sectores")
public class UsuarioSector {

    @EmbeddedId
    private UsuarioSectorId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("sectorId")
    @JoinColumn(name = "id_sector")
    private Sector sector;

    public UsuarioSector() {
    }

    public UsuarioSector(UsuarioSectorId id, Usuario usuario, Sector sector) {
        this.id = id;
        this.usuario = usuario;
        this.sector = sector;
    }
    
    public UsuarioSector(Usuario usuario, Sector sector) {
        this.id = new UsuarioSectorId(usuario.getIdUsuario(), sector.getIdSector());
        this.usuario = usuario;
        this.sector = sector;
    }

    public UsuarioSectorId getId() {
        return id;
    }

    public void setId(UsuarioSectorId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}