package coop.bancocredicoop.omnited.message.models;

import coop.bancocredicoop.omnited.exposition.UsuarioDTO;

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
        private UsuarioDTO usuario;
    
        public Long getIdSector() {
            return idSector;
        }

        public void setIdSector(Long sector) {
            this.idSector = sector;
        }

        public UsuarioDTO getUsuario() {
            return usuario;
        }

        public void setUsuario(UsuarioDTO usuario) {
            this.usuario = usuario;
        }
    }
}