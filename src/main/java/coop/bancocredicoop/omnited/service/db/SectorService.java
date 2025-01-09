package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.exposition.ColaDTO;
import coop.bancocredicoop.omnited.exposition.DepartamentoDTO;
import coop.bancocredicoop.omnited.exposition.EstadoDTO;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
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
        // Obtiene los sectores con las relaciones necesarias
        Set<Sector> sectores = sectorRepository.findByIdInWithDetails(sectorIds);

        /**
         * 
            private Long id;
            private String nombre;
            private DepartamentoDTO departamento;
            private Set<EstadoDTO> Estados;
            private Set<HabilidadDTO> Habilidades;
            private Set<ColaDTO> colas;
            private Set<UsuarioDTO> usuarios;
            private Set<GrupoEstadoDTO> grupoEstados;
            private Set<GrupoHabilidadDTO> grupoHabilidades;
         */
        
        return sectores.stream().map(sector -> new SectorDTO(
                sector.getIdSector(),
                sector.getNombre(),
                
                // Mapeo del departamento
                new DepartamentoDTO(
                        sector.getDepartamento().getIdDepartamento(),
                        sector.getDepartamento().getNombre()
                ),
                
                // Mapeo de estados
                sector.getEstados().stream()
                        .map(sectorEstado -> new EstadoDTO(
                        sectorEstado.getEstado().getIdEstado(),
                        sectorEstado.getEstado().getNombre(),
                        sectorEstado.getEstado().getProductivo(),
                        sectorEstado.getEstado().getDedicadoUsuarioFinal()
                ))
                        .collect(Collectors.toSet()),
                
                // Mapeo de habilidades
                sector.getHabilidades().stream()
                        .map(sectorHabilidad -> new HabilidadDTO(
                        sectorHabilidad.getHabilidad().getIdHabilidad(),
                        sectorHabilidad.getHabilidad().getNombre(),
                        null
                ))
                        .collect(Collectors.toSet()),
                
                // Mapeo de colas
                sector.getColas().stream()
                        .map(cola -> new ColaDTO(
                        cola.getIdCola(),
                        cola.getNombre(),
                        new EstrategiaDTO(
                                cola.getEstrategia().getIdEstrategia(),
                                cola.getEstrategia().getNombre()
                        ),
                        cola.getRingueo(),
                        cola.getEspera(),
                        cola.getAutoPausa(),
                        cola.getDesborde(),
                        cola.getPrioridad(),
                        cola.getHabilidades().stream().map(ch -> new HabilidadDTO(
                        ch.getHabilidad().getIdHabilidad(),
                        ch.getHabilidad().getNombre(),
                        null
                )).collect(Collectors.toSet())
                ))
                        .collect(Collectors.toSet()),
                // Mapeo de usuarios
                sector.getUsuarios().stream()
                        .map(usuarioSector -> new UsuarioDTO(
                        usuarioSector.getUsuario().getIdUsuario(),
                        usuarioSector.getUsuario().getNombre(),
                        usuarioSector.getUsuario().getApellido(),
                        usuarioSector.getUsuario().getUsuario(),
                        usuarioSector.getUsuario().getCorreo(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ))
                        .collect(Collectors.toSet()),
                
                // Mapeo de Grupos de Estados
                sector.getGruposEstado().stream()
                        .map(grupoEstado -> new GrupoEstadoDTO(
                        grupoEstado.getIdGrupoEstado(),
                        grupoEstado.getNombre(),
                        grupoEstado.getEstados().stream()
                            .map(sectorEstado -> new EstadoDTO(
                                sectorEstado.getEstado().getIdEstado(),
                                sectorEstado.getEstado().getNombre(),
                                sectorEstado.getEstado().getProductivo(),
                                sectorEstado.getEstado().getDedicadoUsuarioFinal()
                            )).collect(Collectors.toSet()),
                        null
                )).collect(Collectors.toSet()),
                
                // Mapeo de Grupos de Habilidades
                sector.getGruposHabilidad().stream()
                        .map(grupoHabilidad -> new GrupoHabilidadDTO(
                        grupoHabilidad.getId(),
                        grupoHabilidad.getNombre(),
                        grupoHabilidad.getHabilidades().stream()
                            .map(sectorHabilidad -> new HabilidadDTO(
                                sectorHabilidad.getHabilidad().getIdHabilidad(),
                                sectorHabilidad.getHabilidad().getNombre(),
                                null
                            )).collect(Collectors.toSet()),
                        null
                )).collect(Collectors.toSet())
                
        )).collect(Collectors.toSet());
    }
}