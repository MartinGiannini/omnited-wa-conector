package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Cola;
import coop.bancocredicoop.omnited.entity.ColaHabilidad;
import coop.bancocredicoop.omnited.entity.Habilidad;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
import coop.bancocredicoop.omnited.repository.ColaHabilidadRepository;
import coop.bancocredicoop.omnited.repository.HabilidadRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaHabilidadService {

    private final ColaHabilidadRepository colaHabilidadRepository;
    private final HabilidadRepository habilidadRepository;

    public ColaHabilidadService(
            ColaHabilidadRepository colaHabilidadRepository,
            HabilidadRepository habilidadRepository
    ) {
        this.colaHabilidadRepository = colaHabilidadRepository;
        this.habilidadRepository = habilidadRepository;
    }

    /**
     * Asocia habilidades a una cola específica.
     *
     * @param cola La cola a la que se le asociarán las habilidades.
     * @param habilidadesDTOs El conjunto de habilidades que se asociarán.
     */
    @Transactional
    public void asociarHabilidadesACola(Cola cola, Set<HabilidadDTO> habilidadesDTOs) {
        Set<ColaHabilidad> habilidadesAsociadas = habilidadesDTOs.stream()
                .map(habilidadDTO -> {
                    Habilidad habilidad = habilidadRepository.findById(habilidadDTO.getIdHabilidad())
                            .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + habilidadDTO.getIdHabilidad()));

                    ColaHabilidad colaHabilidad = new ColaHabilidad();
                    colaHabilidad.setCola(cola);
                    colaHabilidad.setHabilidad(habilidad);
                    return colaHabilidad;
                })
                .collect(Collectors.toSet());

        colaHabilidadRepository.saveAll(habilidadesAsociadas);
    }
}
