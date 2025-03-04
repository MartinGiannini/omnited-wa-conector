package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class SessionDTO {

    private Long idUsuario;
    private Long idPerfil;
    private Set<Long> idSectores;

    public SessionDTO(Long idUsuario, Long idPerfil, Set<Long> idSectores) {
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
        this.idSectores = idSectores;
    }

    public SessionDTO() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Set<Long> getIdSectores() {
        return idSectores;
    }

    public void setIdSectores(Set<Long> idSectores) {
        this.idSectores = idSectores;
    }

    @Override
    public String toString() {
        return "PerfilSectorDTO{"
                + "idUsuario=" + idUsuario
                + "idPerfil=" + idPerfil
                + ", idSectores=" + idSectores
                + '}';
    }
}
