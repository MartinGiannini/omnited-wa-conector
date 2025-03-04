package coop.bancocredicoop.omnited.service.db;

import coop.bancocredicoop.omnited.entity.Sector;
import coop.bancocredicoop.omnited.entity.Usuario;
import coop.bancocredicoop.omnited.entity.UsuarioSector;
import coop.bancocredicoop.omnited.repository.UsuarioRepository;
import coop.bancocredicoop.omnited.repository.UsuarioSectorRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioSectorService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioSectorRepository usuarioSectorRepository;

    public UsuarioSectorService(
            UsuarioRepository usuarioRepository,
            UsuarioSectorRepository usuarioSectorRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioSectorRepository = usuarioSectorRepository;
    }

    @Transactional
    public void guardarUsuarioConSectores(Long idUsuario, Set<Sector> sectores) {
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + idUsuario));

        // Eliminar relaciones previas en usuarios_sectores
        usuarioSectorRepository.deleteByUsuarioId(idUsuario);

        // Crear nuevas asociaciones usuario-sector
        Set<UsuarioSector> usuarioSectores = sectores.stream().map(sector -> {
            return new UsuarioSector(usuario, sector);
        }).collect(Collectors.toSet());

        // Guardar nuevas relaciones en la base de datos
        usuarioSectorRepository.saveAll(usuarioSectores);
    }
}
