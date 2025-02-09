package coop.bancocredicoop.omnited.message.models;

import coop.bancocredicoop.omnited.exposition.SectorDTO;
import java.util.Set;

public class SectorDatos {
    private SectoresWrapper loginSectoresWS;

    // Getters y setters
    public SectoresWrapper getSectoresDatos() {
        return loginSectoresWS;
    }

    public void setSectoresDatos(SectoresWrapper sectoresDatos) {
        this.loginSectoresWS = sectoresDatos;
    }

    public static class SectoresWrapper {
        private String usuario;
        private Set<SectorInput> sectores;

        // Getters y setters
        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public Set<SectorInput> getSectores() {
            return sectores;
        }

        public void setSectores(Set<SectorInput> sectores) {
            this.sectores = sectores;
        }
    }
    
    public static class SectorInput {
        private Long idSector;
        private String sectorNombre;
        private String sectorDepartamento;
        private String sectorCola;
        private String sectorUsuario;
        private String sectorHabilidad;
        private String sectorEstado;
        private String sectorGrupoEstado;
        private String sectorGrupoHabilidad;
        
        // Getters y setters
        public Long getIdSector() {
            return idSector;
        }

        public void setIdSector(Long id) {
            this.idSector = id;
        }

        public String getSectorNombre() {
            return sectorNombre;
        }

        public void setSectorNombre(String nombre) {
            this.sectorNombre = nombre;
        }

        public String getSectorDepartamento() {
            return sectorDepartamento;
        }

        public void setSectorDepartamento(String departamento) {
            this.sectorDepartamento = departamento;
        }

        public String getSectorCola() {
            return sectorCola;
        }

        public void setSectorCola(String colas) {
            this.sectorCola = colas;
        }

        public String getSectorUsuario() {
            return sectorUsuario;
        }

        public void setSectorUsuario(String usuarios) {
            this.sectorUsuario = usuarios;
        }

        public String getSectorHabilidad() {
            return sectorHabilidad;
        }

        public void setSectorHabilidad(String habilidades) {
            this.sectorHabilidad = habilidades;
        }

        public String getSectorEstado() {
            return sectorEstado;
        }

        public void setSectorEstado(String estados) {
            this.sectorEstado = estados;
        }

        public String getSectorGrupoEstado() {
            return sectorGrupoEstado;
        }

        public void setSectorGrupoEstado(String grupoEstados) {
            this.sectorGrupoEstado = grupoEstados;
        }

        public String getSectorGrupoHabilidad() {
            return sectorGrupoHabilidad;
        }

        public void setSectorGrupoHabilidad(String grupoHabilidades) {
            this.sectorGrupoHabilidad = grupoHabilidades;
        }
    }
}