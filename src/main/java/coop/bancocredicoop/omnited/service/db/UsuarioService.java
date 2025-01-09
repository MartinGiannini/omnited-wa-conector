package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Usuario;
import coop.bancocredicoop.omnited.exposition.CondicionDTO;
import coop.bancocredicoop.omnited.exposition.EstadoDTO;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
import coop.bancocredicoop.omnited.exposition.PerfilDTO;
import coop.bancocredicoop.omnited.exposition.PermisoDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.repository.UsuarioRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioDTO getUsuarioByUsuario(String usuario) {
        Usuario entity = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setUsuario(entity.getUsuario());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setCorreo(entity.getCorreo());

        // Perfil
        PerfilDTO perfilDTO = new PerfilDTO();
        perfilDTO.setId(entity.getPerfil().getIdPerfil());
        perfilDTO.setNombre(entity.getPerfil().getNombre());
        dto.setPerfil(perfilDTO);

        // Condición
        CondicionDTO condicionDTO = new CondicionDTO();
        condicionDTO.setId(entity.getCondicion().getIdCondicion());
        condicionDTO.setNombre(entity.getCondicion().getNombre());
        dto.setCondicion(condicionDTO);

        // Habilidades
        dto.setHabilidades(entity.getUsuarioHabilidades().stream()
                .map(uh -> {
                    HabilidadDTO habilidadDTO = new HabilidadDTO();
                    habilidadDTO.setId(uh.getHabilidad().getIdHabilidad());
                    habilidadDTO.setNombre(uh.getHabilidad().getNombre());
                    habilidadDTO.setValor(uh.getValor());
                    return habilidadDTO;
                })
                .collect(Collectors.toSet()));

        // Estados
        dto.setEstados(entity.getUsuarioEstados().stream()
                .map(ue -> {
                    EstadoDTO estadoDTO = new EstadoDTO();
                    estadoDTO.setId(ue.getEstado().getIdEstado());
                    estadoDTO.setNombre(ue.getEstado().getNombre());
                    estadoDTO.setProductivo(ue.getEstado().getProductivo());
                    estadoDTO.setDedicadoUsuarioFinal(ue.getEstado().getDedicadoUsuarioFinal());
                    return estadoDTO;
                })
                .collect(Collectors.toSet()));

        // Sectores
        dto.setSectores(entity.getUsuarioSectores().stream()
                .map(us -> {
                    SectorDTO sectorDTO = new SectorDTO();
                    sectorDTO.setId(us.getSector().getIdSector());
                    sectorDTO.setNombre(us.getSector().getNombre());
                    return sectorDTO;
                })
                .collect(Collectors.toSet()));

        // Permisos
        dto.setPermisosAdministracion(entity.getPermisosAdministracion().stream()
                .map(pa -> {
                    PermisoDTO permisoDTO = new PermisoDTO();
                    permisoDTO.setId(pa.getIdUsuarioPermisoAdministracion());
                    permisoDTO.setNombre(pa.getPermiso().getNombre());
                    return permisoDTO;
                })
                .collect(Collectors.toSet()));

        dto.setPermisosSupervision(entity.getPermisosSupervision().stream()
                .map(ps -> {
                    PermisoDTO permisoDTO = new PermisoDTO();
                    permisoDTO.setId(ps.getIdUsuarioPermisoSupervision());
                    permisoDTO.setNombre(ps.getPermiso().getNombre());
                    return permisoDTO;
                })
                .collect(Collectors.toSet()));

        dto.setPermisosOperacion(entity.getPermisosOperacion().stream()
                .map(po -> {
                    PermisoDTO permisoDTO = new PermisoDTO();
                    permisoDTO.setId(po.getIdUsuarioPermisoOperacion());
                    permisoDTO.setNombre(po.getPermiso().getNombre());
                    return permisoDTO;
                })
                .collect(Collectors.toSet()));

        return dto;
    }

}
