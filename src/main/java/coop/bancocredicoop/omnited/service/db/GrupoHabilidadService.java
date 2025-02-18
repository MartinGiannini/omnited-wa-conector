package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.GrupoHabilidad;
import coop.bancocredicoop.omnited.entity.GrupoHabilidadHabilidad;
import coop.bancocredicoop.omnited.entity.Habilidad;
import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.exposition.DepartamentoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.repository.GrupoHabilidadHabilidadRepository;
import coop.bancocredicoop.omnited.repository.GrupoHabilidadRepository;
import coop.bancocredicoop.omnited.repository.HabilidadRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoHabilidadService {

    private final GrupoHabilidadRepository grupoHabilidadRepository;
    private final GrupoHabilidadHabilidadRepository grupoHabilidadHabilidadRepository;
    private final HabilidadRepository habilidadRepository;

    public GrupoHabilidadService(
            GrupoHabilidadRepository grupoHabilidadRepository,
            GrupoHabilidadHabilidadRepository grupoHabilidadHabilidadRepository,
            HabilidadRepository habilidadRepository) {
        this.grupoHabilidadRepository = grupoHabilidadRepository;
        this.grupoHabilidadHabilidadRepository = grupoHabilidadHabilidadRepository;
        this.habilidadRepository = habilidadRepository;
    }

    @Transactional
    public Set<GrupoHabilidadDTO> getGrupoHabilidadesBySectores(Set<Long> sectorIds) {
        Set<GrupoHabilidad> grupoHabilidades = grupoHabilidadRepository.findGrupoHabilidadesBySectores(sectorIds);

        return grupoHabilidades.stream()
                .map(gh -> new GrupoHabilidadDTO(
                gh.getIdGrupoHabilidad(),
                gh.getGrupoHabilidadNombre(),
                gh.getGrupoHabilidadHabilidad().stream()
                        .map(h -> new HabilidadDTO(
                        h.getHabilidad().getIdHabilidad(),
                        h.getHabilidad().getHabilidadNombre(),
                        h.getGrupoHabilidadValor()
                ))
                        .collect(Collectors.toSet()),
                new SectorDTO( // Mapear el sector
                        gh.getSector().getIdSector(),
                        gh.getSector().getSectorNombre(),
                        new DepartamentoDTO(gh.getSector().getSectorDepartamento().getIdDepartamento(), gh.getSector().getSectorDepartamento().getDepartamentoNombre()),
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

    @Transactional
    public void actualizarGrupoHabilidad(GrupoHabilidadDTO grupoHabilidadDTO) {
        // Verificar si existe el grupo de habilidades
        GrupoHabilidad grupoHabilidad = grupoHabilidadRepository.findById(grupoHabilidadDTO.getIdGrupoHabilidad())
                .orElseThrow(() -> new EntityNotFoundException("GrupoHabilidad no encontrado con ID: " + grupoHabilidadDTO.getIdGrupoHabilidad()));

        // Eliminar habilidades previas
        grupoHabilidadRepository.deleteHabilidadesByGrupoHabilidad(grupoHabilidad.getIdGrupoHabilidad());

        // Asociar las nuevas habilidades al grupo
        Set<GrupoHabilidadHabilidad> nuevasHabilidades = grupoHabilidadDTO.getHabilidad().stream()
                .map(habilidadDTO -> {
                    Habilidad habilidad = habilidadRepository.findById(habilidadDTO.getIdHabilidad())
                            .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + habilidadDTO.getIdHabilidad()));

                    GrupoHabilidadHabilidad grupoHabilidadHabilidad = new GrupoHabilidadHabilidad();
                    grupoHabilidadHabilidad.setGrupoHabilidad(grupoHabilidad);
                    grupoHabilidadHabilidad.setHabilidad(habilidad);
                    grupoHabilidadHabilidad.setGrupoHabilidadValor(habilidadDTO.getHabilidadValor());
                    return grupoHabilidadHabilidad;
                })
                .collect(Collectors.toSet());

        // Guardar las nuevas asociaciones en la base de datos
        grupoHabilidadHabilidadRepository.saveAll(nuevasHabilidades);
    }

    @Transactional
    public GrupoHabilidadDTO guardarGrupoHabilidad(Long idSector, GrupoHabilidadDTO grupoHabilidadDTO) {

        Sector sector = new Sector();
        sector.setIdSector(idSector);

        GrupoHabilidad grupoHabilidad = new GrupoHabilidad();
        grupoHabilidad.setSector(sector);
        grupoHabilidad.setGrupoHabilidadNombre(grupoHabilidadDTO.getGrupoHabilidadNombre());

        // Asociar habilidades al grupo
        Set<GrupoHabilidadHabilidad> habilidades = grupoHabilidadDTO.getHabilidad().stream().map(habilidadInput -> {
            Habilidad habilidad = habilidadRepository.findById(habilidadInput.getIdHabilidad())
                    .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + habilidadInput.getIdHabilidad()));

            GrupoHabilidadHabilidad grupoHabilidadHabilidad = new GrupoHabilidadHabilidad();
            grupoHabilidadHabilidad.setGrupoHabilidad(grupoHabilidad);
            grupoHabilidadHabilidad.setHabilidad(habilidad);
            grupoHabilidadHabilidad.setGrupoHabilidadValor(habilidadInput.getHabilidadValor());

            return grupoHabilidadHabilidad;
        }).collect(Collectors.toSet());

        grupoHabilidad.setGrupoHabilidadHabilidad(habilidades);

        // Guardar el grupo con las habilidades asociadas
        grupoHabilidadRepository.save(grupoHabilidad);

        return convertirAGrupoHabilidadDTO(grupoHabilidad);
    }

    public GrupoHabilidadDTO convertirAGrupoHabilidadDTO(GrupoHabilidad grupoHabilidad) {
        GrupoHabilidadDTO dto = new GrupoHabilidadDTO();
        dto.setIdGrupoHabilidad(grupoHabilidad.getIdGrupoHabilidad());
        dto.setGrupoHabilidadNombre(grupoHabilidad.getGrupoHabilidadNombre());

        // Conversión de habilidades
        Set<HabilidadDTO> habilidades = grupoHabilidad.getGrupoHabilidadHabilidad().stream()
                .map(ghh -> new HabilidadDTO(
                ghh.getHabilidad().getIdHabilidad(),
                ghh.getHabilidad().getHabilidadNombre(),
                ghh.getGrupoHabilidadValor()
        ))
                .collect(Collectors.toSet());
        dto.setHabilidad(habilidades);

        // Si es necesario, asignar SectorDTO (dependiendo de la implementación)
        dto.setSector(convertirSectorADTO(grupoHabilidad.getSector()));
        return dto;
    }

    public SectorDTO convertirSectorADTO(Sector sector) {
        
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setIdSector(sector.getIdSector());
        sectorDTO.setSectorNombre(sector.getSectorNombre());
        
        return sectorDTO;
    }
}
