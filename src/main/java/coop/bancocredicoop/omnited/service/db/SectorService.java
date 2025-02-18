package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.exposition.ColaDTO;
import coop.bancocredicoop.omnited.exposition.CondicionDTO;
import coop.bancocredicoop.omnited.exposition.DepartamentoDTO;
import coop.bancocredicoop.omnited.exposition.EstadoDTO;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import coop.bancocredicoop.omnited.exposition.ExtensionDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
import coop.bancocredicoop.omnited.exposition.PerfilDTO;
import coop.bancocredicoop.omnited.exposition.PermisoDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.repository.SectorRepository;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Transactional
    public Set<SectorDTO> getSectoresByUsuarioSectores(Set<Long> sectorIds) {
        
        Set<Sector> sectores = sectorRepository.findByIdInWithDetails(sectorIds);
        
        return sectores.stream().map(sector -> new SectorDTO(
                sector.getIdSector(),
                sector.getSectorNombre(),
                // Mapeo del departamento
                new DepartamentoDTO(
                        sector.getSectorDepartamento().getIdDepartamento(),
                        sector.getSectorDepartamento().getDepartamentoNombre()
                ),
                // Mapeo de estados
                sector.getSectorEstado().stream()
                        .map(sectorEstado -> new EstadoDTO(
                        sectorEstado.getEstado().getIdEstado(),
                        sectorEstado.getEstado().getEstadoNombre(),
                        sectorEstado.getEstado().getEstadoProductivo(),
                        sectorEstado.getEstado().getEstadoDedicadoUsuarioFinal()
                ))
                        .collect(Collectors.toSet()),
                // Mapeo de habilidades
                sector.getSectorHabilidad().stream()
                        .map(sectorHabilidad -> new HabilidadDTO(
                        sectorHabilidad.getHabilidad().getIdHabilidad(),
                        sectorHabilidad.getHabilidad().getHabilidadNombre(),
                        null
                ))
                        .collect(Collectors.toSet()),
                // Mapeo de colas
                sector.getCola().stream()
                        .map(cola -> new ColaDTO(
                        cola.getIdCola(),
                        cola.getColaNombre(),
                        new EstrategiaDTO(
                                cola.getEstrategia().getIdEstrategia(),
                                cola.getEstrategia().getEstrategiaNombre()
                        ),
                        cola.getColaRingueo(),
                        cola.getColaEspera(),
                        cola.getColaAutoPausa(),
                        cola.getColaDesborde(),
                        cola.getColaPrioridad(),
                        cola.getColaHabilidad().stream().map(ch -> new HabilidadDTO(
                        ch.getHabilidad().getIdHabilidad(),
                        ch.getHabilidad().getHabilidadNombre(),
                        null
                )).collect(Collectors.toSet())
                ))
                        .collect(Collectors.toSet()),
                // Mapeo de usuarios
                sector.getUsuarioSector().stream()
                        .map(usuarioSector -> new UsuarioDTO(
                        usuarioSector.getUsuario().getIdUsuario(),
                        usuarioSector.getUsuario().getUsuarioNombre(),
                        usuarioSector.getUsuario().getUsuarioApellido(),
                        usuarioSector.getUsuario().getUsuarioUsuario(),
                        usuarioSector.getUsuario().getUsuarioCorreo(),
                        new ExtensionDTO(
                                usuarioSector.getUsuario().getUsuarioExtension().getIdExtension(),
                                usuarioSector.getUsuario().getUsuarioExtension().getExtensionUri(),
                                usuarioSector.getUsuario().getUsuarioExtension().getExtensionServer(),
                                usuarioSector.getUsuario().getUsuarioExtension().getExtensionDominio(),
                                usuarioSector.getUsuario().getUsuarioExtension().getExtensionUsername(),
                                usuarioSector.getUsuario().getUsuarioExtension().getExtensionServer()
                        ),
                        new PerfilDTO(
                                usuarioSector.getUsuario().getUsuarioPerfil().getIdPerfil(),
                                usuarioSector.getUsuario().getUsuarioPerfil().getPerfilNombre()
                        ),
                        new CondicionDTO(
                                usuarioSector.getUsuario().getUsuarioCondicion().getIdCondicion(),
                                usuarioSector.getUsuario().getUsuarioCondicion().getCondicionNombre()
                        ),
                        usuarioSector.getUsuario().getUsuarioHabilidad().stream()
                                .map(uh -> {
                                    HabilidadDTO habilidadDTO = new HabilidadDTO();
                                    habilidadDTO.setIdHabilidad(uh.getHabilidad().getIdHabilidad());
                                    habilidadDTO.setHabilidadNombre(uh.getHabilidad().getHabilidadNombre());
                                    habilidadDTO.setHabilidadValor(uh.getUsuarioHabilidadValor());
                                    return habilidadDTO;
                                })
                                .collect(Collectors.toSet()),
                        usuarioSector.getUsuario().getUsuarioEstado().stream()
                                .map(ue -> {
                                    EstadoDTO estadoDTO = new EstadoDTO();
                                    estadoDTO.setIdEstado(ue.getEstado().getIdEstado());
                                    estadoDTO.setEstadoNombre(ue.getEstado().getEstadoNombre());
                                    estadoDTO.setEstadoProductivo(ue.getEstado().getEstadoProductivo());
                                    estadoDTO.setEstadoDedicadoUsuarioFinal(ue.getEstado().getEstadoDedicadoUsuarioFinal());
                                    return estadoDTO;
                                })
                                .collect(Collectors.toSet()),
                        null,
                        null,
                        null,
                        usuarioSector.getUsuario().getUsuarioPermisoOperacion().stream()
                                .map(po -> {
                                    PermisoDTO permisoDTO = new PermisoDTO();
                                    permisoDTO.setIdPermiso(po.getPermisoOperacion().getIdPermisoOperacion());
                                    permisoDTO.setPermisoNombre(po.getPermisoOperacion().getPermisoOperacionNombre());
                                    return permisoDTO;
                                })
                                .collect(Collectors.toSet())
                ))
                        .collect(Collectors.toSet()),
                // Mapeo de Grupos de Estados
                sector.getGrupoEstado().stream()
                        .map(grupoEstado -> new GrupoEstadoDTO(
                        grupoEstado.getIdGrupoEstado(),
                        grupoEstado.getGrupoEstadoNombre(),
                        grupoEstado.getGrupoEstadoEstado().stream()
                                .map(sectorEstado -> new EstadoDTO(
                                sectorEstado.getEstado().getIdEstado(),
                                sectorEstado.getEstado().getEstadoNombre(),
                                sectorEstado.getEstado().getEstadoProductivo(),
                                sectorEstado.getEstado().getEstadoDedicadoUsuarioFinal()
                        )).collect(Collectors.toSet()),
                        null
                )).collect(Collectors.toSet()),
                // Mapeo de Grupos de Habilidades
                sector.getGrupoHabilidad().stream()
                        .map(grupoHabilidad -> new GrupoHabilidadDTO(
                        grupoHabilidad.getIdGrupoHabilidad(),
                        grupoHabilidad.getGrupoHabilidadNombre(),
                        grupoHabilidad.getGrupoHabilidadHabilidad().stream()
                                .map(sectorHabilidad -> new HabilidadDTO(
                                sectorHabilidad.getHabilidad().getIdHabilidad(),
                                sectorHabilidad.getHabilidad().getHabilidadNombre(),
                                sectorHabilidad.getGrupoHabilidadValor()
                        )).collect(Collectors.toSet()),
                        null
                )).collect(Collectors.toSet())
        )
        ).collect(Collectors.toSet());
    }
}
