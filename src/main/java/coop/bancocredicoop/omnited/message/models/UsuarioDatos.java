package coop.bancocredicoop.omnited.message.models;

import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import java.util.Set;

public class UsuarioDatos {
    
    private IngresoDatos ingresoDatos;

    public IngresoDatos getIngresoDatos() {
        return ingresoDatos;
    }
    
    public void setIngresoDatos(IngresoDatos ingresoDatos) {
        this.ingresoDatos = ingresoDatos;
    }

    public static class IngresoDatos {
        private Long idSector;
        private Set<Sector> sectores;
        private UsuarioDTO usuario;
    
        public Long getIdSector() {
            return idSector;
        }

        public void setIdSector(Long sector) {
            this.idSector = sector;
        }

        public Set<Sector> getSectores() {
            return sectores;
        }

        public void setSectores(Set<Sector> sectores) {
            this.sectores = sectores;
        }

        public UsuarioDTO getUsuario() {
            return usuario;
        }

        public void setUsuario(UsuarioDTO usuario) {
            this.usuario = usuario;
        }
    }
}