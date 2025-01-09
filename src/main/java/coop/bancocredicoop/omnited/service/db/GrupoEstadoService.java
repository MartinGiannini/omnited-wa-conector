package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.GrupoEstado;
import coop.bancocredicoop.omnited.exposition.DepartamentoDTO;
import coop.bancocredicoop.omnited.exposition.EstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.repository.GrupoEstadoRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoEstadoService {

    private final GrupoEstadoRepository grupoEstadoRepository;

    public GrupoEstadoService(GrupoEstadoRepository grupoEstadoRepository) {
        this.grupoEstadoRepository = grupoEstadoRepository;
    }

    @Transactional
    public Set<GrupoEstadoDTO> getGrupoEstadosBySectores(Set<Long> sectorIds) {

        Set<GrupoEstado> grupoEstados = grupoEstadoRepository.findGrupoEstadosBySectores(sectorIds);

        return grupoEstados.stream()
                .map(ge -> new GrupoEstadoDTO(
                ge.getIdGrupoEstado(),
                ge.getNombre(),
                ge.getEstados().stream()
                        .map(ges -> new EstadoDTO(
                        ges.getEstado().getIdEstado(),
                        ges.getEstado().getNombre(),
                        ges.getEstado().getProductivo(),
                        ges.getEstado().getDedicadoUsuarioFinal()
                ))
                        .collect(Collectors.toSet()),
                new SectorDTO(
                        ge.getSector().getIdSector(),
                        ge.getSector().getNombre(),
                        new DepartamentoDTO(
                                ge.getSector().getDepartamento().getIdDepartamento(),
                                ge.getSector().getDepartamento().getNombre()
                        ),
                        null, // Otras relaciones del sector
                        null,
                        null,
                        null,
                        null,
                        null
                )
        ))
                .collect(Collectors.toSet());
    }
}
