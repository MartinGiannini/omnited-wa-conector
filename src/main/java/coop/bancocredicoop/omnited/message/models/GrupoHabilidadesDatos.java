package coop.bancocredicoop.omnited.message.models;

import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;

public class GrupoHabilidadesDatos {

    private IngresoDatos ingresoDatos;

    public IngresoDatos getIngresoDatos() {
        return ingresoDatos;
    }

    public void setIngresoDatos(IngresoDatos ingresoDatos) {
        this.ingresoDatos = ingresoDatos;
    }

    public static class IngresoDatos {
        private Long idSector;
        private GrupoHabilidadDTO grupoDatos;

        public Long getIdSector() {
            return idSector;
        }

        public void setIdSector(Long idSector) {
            this.idSector = idSector;
        }

        public GrupoHabilidadDTO getGrupoDatos() {
            return grupoDatos;
        }

        public void setGrupoDatos(GrupoHabilidadDTO grupoDatos) {
            this.grupoDatos = grupoDatos;
        }
    }
}