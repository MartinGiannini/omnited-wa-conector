package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.exposition.PermisoDTO;
import coop.bancocredicoop.omnited.exposition.PermisosPorCategoriaDTO;
import coop.bancocredicoop.omnited.repository.PermisoAdministracionRepository;
import coop.bancocredicoop.omnited.repository.PermisoOperacionRepository;
import coop.bancocredicoop.omnited.repository.PermisoSupervisionRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PermisoService {

    private final PermisoOperacionRepository permisoOperacionRepository;
    private final PermisoSupervisionRepository permisoSupervisionRepository;
    private final PermisoAdministracionRepository permisoAdministracionRepository;

    public PermisoService(PermisoOperacionRepository permisoOperacionRepository,
                          PermisoSupervisionRepository permisoSupervisionRepository,
                          PermisoAdministracionRepository permisoAdministracionRepository) {
        this.permisoOperacionRepository = permisoOperacionRepository;
        this.permisoSupervisionRepository = permisoSupervisionRepository;
        this.permisoAdministracionRepository = permisoAdministracionRepository;
    }

    public PermisosPorCategoriaDTO getPermisosPorCategoria() {
        // Permisos por tipo
        Set<PermisoDTO> permisosAdministracion = permisoAdministracionRepository.findAllPermisosAdministracion()
                .stream()
                .map(pa -> new PermisoDTO(pa.getIdPermisoAdministracion(), pa.getPermisoAdministracionNombre()))
                .collect(Collectors.toSet());

        Set<PermisoDTO> permisosSupervision = permisoSupervisionRepository.findAllPermisosSupervision()
                .stream()
                .map(ps -> new PermisoDTO(ps.getIdPermisoSupervision(), ps.getPermisoSupervisionNombre()))
                .collect(Collectors.toSet());

        Set<PermisoDTO> permisosOperacion = permisoOperacionRepository.findAllPermisosOperacion()
                .stream()
                .map(po -> new PermisoDTO(po.getIdPermisoOperacion(), po.getPermisoOperacionNombre()))
                .collect(Collectors.toSet());

        // Retornar agrupados
        return new PermisosPorCategoriaDTO(permisosAdministracion, permisosSupervision, permisosOperacion);
    }
}