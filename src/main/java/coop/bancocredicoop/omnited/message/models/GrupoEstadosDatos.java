package coop.bancocredicoop.omnited.message.models;

import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;

public class GrupoEstadosDatos {
    
    private IngresoDatos ingresoDatos;

    public IngresoDatos getIngresoDatos() {
        return ingresoDatos;
    }

    public void setIngresoDatos(IngresoDatos ingresoDatos) {
        this.ingresoDatos = ingresoDatos;
    }

    public static class IngresoDatos {
        private Long idSector;
        private GrupoEstadoDTO grupoDatos;

        public Long getIdSector() {
            return idSector;
        }

        public void setIdSector(Long idSector) {
            this.idSector = idSector;
        }

        public GrupoEstadoDTO getGrupoDatos() {
            return grupoDatos;
        }

        public void setGrupoDatos(GrupoEstadoDTO grupoDatos) {
            this.grupoDatos = grupoDatos;
        }
    }
}