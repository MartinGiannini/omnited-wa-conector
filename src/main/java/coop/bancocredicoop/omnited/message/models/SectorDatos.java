package coop.bancocredicoop.omnited.message.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import java.util.Set;

public class SectorDatos {
    
    private IngresoDatos ingresoDatos;

    public IngresoDatos getIngresoDatos() {
        return ingresoDatos;
    }
    
    public void setIngresoDatos(IngresoDatos ingresoDatos) {
        this.ingresoDatos = ingresoDatos;
    }
    
    public static class IngresoDatos {
        private Long idUsuario;
        
        @JsonInclude(JsonInclude.Include.NON_NULL) // Ignora sectores si es null
        private Set<SectorDTO> sectores;

        public Long getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
        }

        public Set<SectorDTO> getSectores() {
            return sectores;
        }

        public void setSectores(Set<SectorDTO> sectores) {
            this.sectores = sectores;
        }
    }
}