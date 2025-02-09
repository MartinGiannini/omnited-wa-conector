package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Estado;
import coop.bancocredicoop.omnited.entity.GrupoEstado;
import coop.bancocredicoop.omnited.entity.GrupoEstadoEstado;
import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.exposition.DepartamentoDTO;
import coop.bancocredicoop.omnited.exposition.EstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.repository.EstadoRepository;
import coop.bancocredicoop.omnited.repository.GrupoEstadoEstadoRepository;
import coop.bancocredicoop.omnited.repository.GrupoEstadoRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoEstadoService {

    private final EstadoRepository estadoRepository;
    private final GrupoEstadoRepository grupoEstadoRepository;
    private final GrupoEstadoEstadoRepository grupoEstadoEstadoRepository;

    public GrupoEstadoService(
            EstadoRepository estadoRepository,
            GrupoEstadoRepository grupoEstadoRepository,
            GrupoEstadoEstadoRepository grupoEstadoEstadoRepository) {
        this.estadoRepository = estadoRepository;
        this.grupoEstadoRepository = grupoEstadoRepository;
        this.grupoEstadoEstadoRepository = grupoEstadoEstadoRepository;
    }

    @Transactional
    public Set<GrupoEstadoDTO> getGrupoEstadosBySectores(Set<Long> sectorIds) {

        Set<GrupoEstado> grupoEstados = grupoEstadoRepository.findGrupoEstadosBySectores(sectorIds);

        return grupoEstados.stream()
                .map(ge -> new GrupoEstadoDTO(
                ge.getIdGrupoEstado(),
                ge.getGrupoEstadoNombre(),
                ge.getGrupoEstadoEstado().stream()
                        .map(ges -> new EstadoDTO(
                        ges.getEstado().getIdEstado(),
                        ges.getEstado().getEstadoNombre(),
                        ges.getEstado().getEstadoProductivo(),
                        ges.getEstado().getEstadoDedicadoUsuarioFinal()
                ))
                        .collect(Collectors.toSet()),
                new SectorDTO(
                        ge.getSector().getIdSector(),
                        ge.getSector().getSectorNombre(),
                        new DepartamentoDTO(
                                ge.getSector().getSectorDepartamento().getIdDepartamento(),
                                ge.getSector().getSectorDepartamento().getDepartamentoNombre()
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

    @Transactional
    public void actualizarGrupoEstado(GrupoEstadoDTO grupoEstadoDTO) {
        Long idGrupoEstado = grupoEstadoDTO.getIdGrupoEstado();

        // Obtener el GrupoEstado desde la base de datos
        GrupoEstado grupoEstado = grupoEstadoRepository.findById(idGrupoEstado)
                .orElseThrow(() -> new EntityNotFoundException("GrupoEstado no encontrado: " + idGrupoEstado));

        // Eliminar los estados actuales
        grupoEstadoEstadoRepository.deleteEstadosByGrupoEstado(idGrupoEstado);

        // Agregar los nuevos estados
        Set<GrupoEstadoEstado> nuevosEstados = grupoEstadoDTO.getEstado().stream()
                .map(estadoDTO -> {
                    Estado estado = estadoRepository.findById(estadoDTO.getIdEstado())
                            .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado: " + estadoDTO.getIdEstado()));
                    GrupoEstadoEstado grupoEstadoEstado = new GrupoEstadoEstado();
                    grupoEstadoEstado.setGrupoEstado(grupoEstado);
                    grupoEstadoEstado.setEstado(estado);
                    return grupoEstadoEstado;
                })
                .collect(Collectors.toSet());

        // Guardar los nuevos estados
        grupoEstadoEstadoRepository.saveAll(nuevosEstados);
    }

    @Transactional
    public void guardarGrupoEstado(Long idSector, GrupoEstadoDTO grupoEstadoDTO) {

        Sector sector = new Sector();
        sector.setIdSector(idSector);

        // Crear el nuevo GrupoEstado
        GrupoEstado grupoEstado = new GrupoEstado();
        grupoEstado.setSector(sector);
        grupoEstado.setGrupoEstadoNombre(grupoEstadoDTO.getGrupoEstadoNombre());

        // Asociar estados al grupo
        Set<GrupoEstadoEstado> estados = grupoEstadoDTO.getEstado().stream().map(estadoInput -> {
            Estado estado = estadoRepository.findById(estadoInput.getIdEstado())
                    .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con ID: " + estadoInput.getIdEstado()));

            GrupoEstadoEstado grupoEstadoEstado = new GrupoEstadoEstado();
            grupoEstadoEstado.setGrupoEstado(grupoEstado);
            grupoEstadoEstado.setEstado(estado);

            return grupoEstadoEstado;
        }).collect(Collectors.toSet());

        grupoEstado.setGrupoEstadoEstado(estados);

        // Guardar el grupo con los estados asociados
        grupoEstadoRepository.save(grupoEstado);
    }
}