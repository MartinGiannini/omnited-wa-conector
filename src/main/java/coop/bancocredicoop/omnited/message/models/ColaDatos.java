package coop.bancocredicoop.omnited.message.models;

import coop.bancocredicoop.omnited.exposition.ColaDTO;

public class ColaDatos {
    
    private IngresoDatos ingresoDatos;
    
    public IngresoDatos getIngresoDatos() {
        return ingresoDatos;
    }
    
    public void setIngresoDatos(IngresoDatos ingresoDatos) {
        this.ingresoDatos = ingresoDatos;
    }
    
    public static class IngresoDatos {
        private Long idSector;
        private ColaDTO cola;

        public Long getIdSector() {
            return idSector;
        }

        public void setIdSector(Long sector) {
            this.idSector = sector;
        }

        public ColaDTO getCola() {
            return cola;
        }

        public void setCola(ColaDTO cola) {
            this.cola = cola;
        }

        
    }
}
