package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Cola;
import coop.bancocredicoop.omnited.entity.ColaHabilidad;
import coop.bancocredicoop.omnited.entity.Estrategia;
import coop.bancocredicoop.omnited.entity.Habilidad;
import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.exposition.ColaDTO;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import coop.bancocredicoop.omnited.repository.ColaHabilidadRepository;
import coop.bancocredicoop.omnited.repository.ColaRepository;
import coop.bancocredicoop.omnited.repository.EstrategiaRepository;
import coop.bancocredicoop.omnited.repository.HabilidadRepository;
import coop.bancocredicoop.omnited.repository.SectorRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaService {

    private final SectorRepository sectorRepository;
    private final ColaRepository colaRepository;
    private final EstrategiaRepository estrategiaRepository;
    private final HabilidadRepository habilidadRepository;

    public ColaService(
            SectorRepository sectorRepository,
            ColaRepository colaRepository,
            ColaHabilidadRepository colaHabilidadRepository,
            EstrategiaRepository estrategiaRepository,
            HabilidadRepository habilidadRepository
    ) {
        this.sectorRepository = sectorRepository;
        this.colaRepository = colaRepository;
        this.estrategiaRepository = estrategiaRepository;
        this.habilidadRepository = habilidadRepository;
    }

    @Transactional
    public void colaActualizar(ColaDTO colaDTO) {

        // Recuperar la cola desde la base de datos
        Cola cola = colaRepository.findById(colaDTO.getIdCola())
                .orElseThrow(() -> new EntityNotFoundException("Cola no encontrada con ID: " + colaDTO.getIdCola()));

        cola.setEstrategia(estrategiaRepository.findById(colaDTO.getColaEstrategia().getIdEstrategia())
                .orElseThrow(() -> new EntityNotFoundException("Estrategia no encontrada con ID: " + colaDTO.getColaEstrategia().getIdEstrategia())));

        // Actualizar los valores de la cola
        cola.setColaNombre(colaDTO.getColaNombre());
        cola.setColaEspera(colaDTO.getColaEspera());
        cola.setColaRingueo(colaDTO.getColaRingueo());
        cola.setColaAutoPausa(colaDTO.getColaAutoPausa());
        cola.setColaPrioridad(colaDTO.getColaPrioridad());
        cola.setColaDesborde(colaDTO.getColaDesborde());

        // Asociar nuevas habilidades a la cola (sin eliminar manualmente)
        Set<ColaHabilidad> nuevasHabilidades = colaDTO.getColaHabilidad().stream()
                .map(habilidadDTO -> {
                    Habilidad habilidad = habilidadRepository.findById(habilidadDTO.getIdHabilidad())
                            .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + habilidadDTO.getIdHabilidad()));

                    ColaHabilidad colaHabilidad = new ColaHabilidad();
                    colaHabilidad.setCola(cola);
                    colaHabilidad.setHabilidad(habilidad);
                    return colaHabilidad;
                })
                .collect(Collectors.toSet());

        cola.setColaHabilidad(nuevasHabilidades);

        // Guardar cambios en la BD
        colaRepository.save(cola);
    }

    @Transactional
    public Cola colaAgregar(Long idSector, ColaDTO colaDTO) {

        // Validar si la estrategia existe
        Estrategia estrategia = estrategiaRepository.findById(colaDTO.getColaEstrategia().getIdEstrategia())
                .orElseThrow(() -> new EntityNotFoundException("Estrategia no encontrada con ID: " + colaDTO.getColaEstrategia().getIdEstrategia()));

        Sector sector = sectorRepository.findById(idSector)
                .orElseThrow(() -> new EntityNotFoundException("Sector NO encontrado ID: " + idSector));

        // Crear la nueva Cola
        Cola cola = new Cola();
        cola.setColaNombre(colaDTO.getColaNombre());
        cola.setEstrategia(estrategia);
        cola.setColaEspera(colaDTO.getColaEspera());
        cola.setColaRingueo(colaDTO.getColaRingueo());
        cola.setColaAutoPausa(colaDTO.getColaAutoPausa());
        cola.setColaPrioridad(colaDTO.getColaPrioridad());
        cola.setColaDesborde(colaDTO.getColaDesborde());
        cola.setSector(sector);

        // Guardar la Cola en la base de datos
        cola = colaRepository.save(cola);

        //colaDTO.setIdCola(cola.getIdCola());
        return cola;
    }
}
