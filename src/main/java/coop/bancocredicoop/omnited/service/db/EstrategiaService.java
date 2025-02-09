package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Estrategia;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import coop.bancocredicoop.omnited.repository.EstrategiaRepository;

@Service
public class EstrategiaService {

    private final EstrategiaRepository estrategiaRepository;

    public EstrategiaService(EstrategiaRepository estrategiaRepository) {
        this.estrategiaRepository = estrategiaRepository;
    }

    public Set<EstrategiaDTO> getAllEstrategias() {
        // Obtener todas las estrategias desde el repositorio
        Set<Estrategia> estrategias = estrategiaRepository.findAllEstrategias();

        // Convertir las entidades Estrategia a EstrategiaDTO
        return estrategias.stream()
                .map(estrategia -> new EstrategiaDTO(
                        estrategia.getIdEstrategia(),
                        estrategia.getEstrategiaNombre()
                ))
                .collect(Collectors.toSet());
    }
}