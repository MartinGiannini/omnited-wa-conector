package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.GrupoHabilidad;
import coop.bancocredicoop.omnited.exposition.DepartamentoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.repository.GrupoHabilidadRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoHabilidadService {

    private final GrupoHabilidadRepository grupoHabilidadRepository;

    public GrupoHabilidadService(GrupoHabilidadRepository grupoHabilidadRepository) {
        this.grupoHabilidadRepository = grupoHabilidadRepository;
    }

    @Transactional
    public Set<GrupoHabilidadDTO> getGrupoHabilidadesBySectores(Set<Long> sectorIds) {
        Set<GrupoHabilidad> grupoHabilidades = grupoHabilidadRepository.findGrupoHabilidadesBySectores(sectorIds);

        return grupoHabilidades.stream()
                .map(gh -> new GrupoHabilidadDTO(
                gh.getId(),
                gh.getNombre(),
                gh.getHabilidades().stream()
                        .map(h -> new HabilidadDTO(
                        h.getHabilidad().getIdHabilidad(),
                        h.getHabilidad().getNombre(),
                        h.getGrupoHabilidadValor()
                ))
                        .collect(Collectors.toSet()),
                new SectorDTO( // Mapear el sector
                        gh.getSector().getIdSector(),
                        gh.getSector().getNombre(),
                        new DepartamentoDTO(gh.getSector().getDepartamento().getIdDepartamento(), gh.getSector().getDepartamento().getNombre()),
                        null, // Otras relaciones del sector, si las necesitas
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
