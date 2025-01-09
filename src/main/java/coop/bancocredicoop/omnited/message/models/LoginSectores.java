package coop.bancocredicoop.omnited.message.models;

import java.util.List;

public class LoginSectores {
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
        private List<SectorInput> sectores;

        // Getters y setters
        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public List<SectorInput> getSectores() {
            return sectores;
        }

        public void setSectores(List<SectorInput> sectores) {
            this.sectores = sectores;
        }
    }

    public static class SectorInput {
        private Long id;
        private String nombre;
        private String departamento;
        private String colas;
        private String usuarios;
        private String habilidades;
        private String estados;
        private String grupoEstados;
        private String grupoHabilidades;
        
        // Getters y setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDepartamento() {
            return departamento;
        }

        public void setDepartamento(String departamento) {
            this.departamento = departamento;
        }

        public String getColas() {
            return colas;
        }

        public void setColas(String colas) {
            this.colas = colas;
        }

        public String getUsuarios() {
            return usuarios;
        }

        public void setUsuarios(String usuarios) {
            this.usuarios = usuarios;
        }

        public String getHabilidades() {
            return habilidades;
        }

        public void setHabilidades(String habilidades) {
            this.habilidades = habilidades;
        }

        public String getEstados() {
            return estados;
        }

        public void setEstados(String estados) {
            this.estados = estados;
        }

        public String getGrupoEstados() {
            return grupoEstados;
        }

        public void setGrupoEstados(String grupoEstados) {
            this.grupoEstados = grupoEstados;
        }

        public String getGrupoHabilidades() {
            return grupoHabilidades;
        }

        public void setGrupoHabilidades(String grupoHabilidades) {
            this.grupoHabilidades = grupoHabilidades;
        }
    }
}
