package coop.bancocredicoop.omnited.service.db;

import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import coop.bancocredicoop.omnited.entity.Condicion;
import coop.bancocredicoop.omnited.entity.Estado;
import coop.bancocredicoop.omnited.entity.Extension;
import coop.bancocredicoop.omnited.entity.Habilidad;
import coop.bancocredicoop.omnited.entity.Perfil;
import coop.bancocredicoop.omnited.entity.PermisoOperacion;
import coop.bancocredicoop.omnited.entity.PermisoSupervision;
import coop.bancocredicoop.omnited.entity.Servidor;
import coop.bancocredicoop.omnited.entity.Usuario;
import coop.bancocredicoop.omnited.entity.UsuarioEstado;
import coop.bancocredicoop.omnited.entity.UsuarioHabilidad;
import coop.bancocredicoop.omnited.entity.UsuarioPermisoOperacion;
import coop.bancocredicoop.omnited.entity.UsuarioPermisoSupervision;
import coop.bancocredicoop.omnited.exposition.CondicionDTO;
import coop.bancocredicoop.omnited.exposition.EstadoDTO;
import coop.bancocredicoop.omnited.exposition.ExtensionDTO;
import coop.bancocredicoop.omnited.exposition.HabilidadDTO;
import coop.bancocredicoop.omnited.exposition.PerfilDTO;
import coop.bancocredicoop.omnited.exposition.PermisoDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.repository.EstadoRepository;
import coop.bancocredicoop.omnited.repository.ExtensionRepository;
import coop.bancocredicoop.omnited.repository.HabilidadRepository;
import coop.bancocredicoop.omnited.repository.PermisoOperacionRepository;
import coop.bancocredicoop.omnited.repository.PermisoSupervisionRepository;
import coop.bancocredicoop.omnited.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final HabilidadRepository habilidadRepository;
    private final EstadoRepository estadoRepository;
    private final PermisoOperacionRepository permisoOperacionRepository;
    private final PermisoSupervisionRepository permisoSupervisionRepository;
    private final ExtensionRepository extensionRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository, HabilidadRepository habilidadRepository, EstadoRepository estadoRepository, PermisoOperacionRepository permisoOperacionRepository, PermisoSupervisionRepository permisoSupervisionRepository, ExtensionRepository extensionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.habilidadRepository = habilidadRepository;
        this.estadoRepository = estadoRepository;
        this.permisoOperacionRepository = permisoOperacionRepository;
        this.permisoSupervisionRepository = permisoSupervisionRepository;
        this.extensionRepository = extensionRepository;
    }
    
    @Transactional
    public UsuarioDTO getUsuarioByUsuario(UsuarioDTO usuarioDTO) {
        Usuario entity = usuarioRepository.findByUsuarioUsuario(usuarioDTO.getUsuarioUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setUsuarioUsuario(entity.getUsuarioUsuario());
        dto.setUsuarioNombre(entity.getUsuarioNombre());
        dto.setUsuarioApellido(entity.getUsuarioApellido());
        dto.setUsuarioCorreo(entity.getUsuarioCorreo());

        // Extension
        ExtensionDTO extensionDTO = new ExtensionDTO();
        extensionDTO.setIdExtension(entity.getUsuarioExtension().getIdExtension());
        extensionDTO.setExtensionUri(entity.getUsuarioExtension().getExtensionUri());
        extensionDTO.setExtensionServer(entity.getUsuarioExtension().getExtensionServer());
        extensionDTO.setExtensionDominio(entity.getUsuarioExtension().getExtensionDominio());
        extensionDTO.setExtensionUsername(entity.getUsuarioExtension().getExtensionUsername());
        extensionDTO.setExtensionPassword(entity.getUsuarioExtension().getExtensionPassword());
        dto.setUsuarioExtension(extensionDTO);

        // Perfil
        PerfilDTO perfilDTO = new PerfilDTO();
        perfilDTO.setIdPerfil(entity.getUsuarioPerfil().getIdPerfil());
        perfilDTO.setPerfilNombre(entity.getUsuarioPerfil().getPerfilNombre());
        dto.setUsuarioPerfil(perfilDTO);

        // Condición
        CondicionDTO condicionDTO = new CondicionDTO();
        condicionDTO.setIdCondicion(entity.getUsuarioCondicion().getIdCondicion());
        condicionDTO.setCondicionNombre(entity.getUsuarioCondicion().getCondicionNombre());
        dto.setUsuarioCondicion(condicionDTO);

        // Habilidades
        dto.setUsuarioHabilidad(entity.getUsuarioHabilidad().stream()
                .map(uh -> {
                    HabilidadDTO habilidadDTO = new HabilidadDTO();
                    habilidadDTO.setIdHabilidad(uh.getHabilidad().getIdHabilidad());
                    habilidadDTO.setHabilidadNombre(uh.getHabilidad().getHabilidadNombre());
                    habilidadDTO.setHabilidadValor(uh.getUsuarioHabilidadValor());
                    return habilidadDTO;
                })
                .collect(Collectors.toSet()));

        // Estados
        dto.setUsuarioEstado(entity.getUsuarioEstado().stream()
                .map(ue -> {
                    EstadoDTO estadoDTO = new EstadoDTO();
                    estadoDTO.setIdEstado(ue.getEstado().getIdEstado());
                    estadoDTO.setEstadoNombre(ue.getEstado().getEstadoNombre());
                    estadoDTO.setEstadoProductivo(ue.getEstado().getEstadoProductivo());
                    estadoDTO.setEstadoDedicadoUsuarioFinal(ue.getEstado().getEstadoDedicadoUsuarioFinal());
                    return estadoDTO;
                })
                .collect(Collectors.toSet()));

        // Sectores
        dto.setUsuarioSector(entity.getUsuarioSector().stream()
                .map(us -> {
                    SectorDTO sectorDTO = new SectorDTO();
                    sectorDTO.setIdSector(us.getSector().getIdSector());
                    sectorDTO.setSectorNombre(us.getSector().getSectorNombre());
                    return sectorDTO;
                })
                .collect(Collectors.toSet()));

        // Permisos
        dto.setUsuarioPermisoAdministracion(entity.getUsuarioPermisoAdministracion().stream()
                .map(pa -> {
                    PermisoDTO permisoDTO = new PermisoDTO();
                    permisoDTO.setIdPermiso(pa.getIdUsuarioPermisoAdministracion());
                    permisoDTO.setPermisoNombre(pa.getPermisoAdministracion().getPermisoAdministracionNombre());
                    return permisoDTO;
                })
                .collect(Collectors.toSet()));
        
        dto.setUsuarioPermisoSupervision(entity.getUsuarioPermisoSupervision().stream()
                .map(ps -> {
                    PermisoDTO permisoDTO = new PermisoDTO();
                    permisoDTO.setIdPermiso(ps.getIdUsuarioPermisoSupervision());
                    permisoDTO.setPermisoNombre(ps.getPermisoSupervision().getPermisoSupervisionNombre());
                    return permisoDTO;
                })
                .collect(Collectors.toSet()));
        
        dto.setUsuarioPermisoOperacion(entity.getUsuarioPermisoOperacion().stream()
                .map(po -> {
                    PermisoDTO permisoDTO = new PermisoDTO();
                    permisoDTO.setIdPermiso(po.getIdUsuarioPermisoOperacion());
                    permisoDTO.setPermisoNombre(po.getPermisoOperacion().getPermisoOperacionNombre());
                    return permisoDTO;
                })
                .collect(Collectors.toSet()));
        
        return dto;
    }
    
    @Transactional
    public void actualizaUsuarioHabilidad(UsuarioDTO usuarioDTO) {
        // Recuperar el usuario desde la base de datos
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + usuarioDTO.getIdUsuario()));

        // Actualizar Habilidades
        Set<UsuarioHabilidad> nuevasHabilidades = usuarioDTO.getUsuarioHabilidad().stream()
                .map(habilidadDTO -> {
                    Habilidad habilidad = habilidadRepository.findById(habilidadDTO.getIdHabilidad())
                            .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + habilidadDTO.getIdHabilidad()));
                    
                    UsuarioHabilidad usuarioHabilidad = new UsuarioHabilidad();
                    usuarioHabilidad.setUsuario(usuario);
                    usuarioHabilidad.setHabilidad(habilidad);
                    usuarioHabilidad.setUsuarioHabilidadValor(habilidadDTO.getHabilidadValor());
                    return usuarioHabilidad;
                })
                .collect(Collectors.toSet());
        usuario.setHabilidades(nuevasHabilidades);

        // Guardar cambios en la BD
        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void actualizarUsuarioEstado(UsuarioDTO usuarioDTO) {
        // Recuperar el usuario desde la base de datos
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + usuarioDTO.getIdUsuario()));

        // Actualizar Estados
        Set<UsuarioEstado> nuevosEstados = usuarioDTO.getUsuarioEstado().stream()
                .map(estadoDTO -> {
                    Estado estado = estadoRepository.findById(estadoDTO.getIdEstado())
                            .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con ID: " + estadoDTO.getIdEstado()));
                    
                    UsuarioEstado usuarioEstado = new UsuarioEstado();
                    usuarioEstado.setUsuario(usuario);
                    usuarioEstado.setEstado(estado);
                    return usuarioEstado;
                })
                .collect(Collectors.toSet());
        usuario.setEstados(nuevosEstados);

        // Guardar cambios en la BD
        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void actualizaUsuarioPermisoOperacion(UsuarioDTO usuarioDTO) {
        // Recuperar el usuario desde la base de datos
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + usuarioDTO.getIdUsuario()));

        // Actualizar Permisos
        Set<UsuarioPermisoOperacion> nuevosPermisosOperacion = usuarioDTO.getUsuarioPermisoOperacion().stream()
                .map(permisoDTO -> {
                    PermisoOperacion permisoOperacion = permisoOperacionRepository.findById(permisoDTO.getIdPermiso())
                            .orElseThrow(() -> new EntityNotFoundException("Permiso no encontrado con ID: " + permisoDTO.getIdPermiso()));
                    
                    UsuarioPermisoOperacion usuarioPermisoOperacion = new UsuarioPermisoOperacion();
                    usuarioPermisoOperacion.setUsuario(usuario);
                    usuarioPermisoOperacion.setPermisoOperacion(permisoOperacion);
                    return usuarioPermisoOperacion;
                })
                .collect(Collectors.toSet());
        usuario.setPermisosOperacion(nuevosPermisosOperacion);

        // Guardar cambios en la BD
        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void actualizaUsuarioPermisoSupervision(UsuarioDTO usuarioDTO) {
        // Recuperar el usuario desde la base de datos
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + usuarioDTO.getIdUsuario()));

        // Actualizar Permisos
        Set<UsuarioPermisoSupervision> nuevosPermisosSupervision = usuarioDTO.getUsuarioPermisoSupervision().stream()
                .map(permisoDTO -> {
                    PermisoSupervision permisoSupervision = permisoSupervisionRepository.findById(permisoDTO.getIdPermiso())
                            .orElseThrow(() -> new EntityNotFoundException("Permiso no encontrado con ID: " + permisoDTO.getIdPermiso()));
                    
                    UsuarioPermisoSupervision usuarioPermisoSupervision = new UsuarioPermisoSupervision();
                    usuarioPermisoSupervision.setUsuario(usuario);
                    usuarioPermisoSupervision.setPermisoSupervision(permisoSupervision);
                    return usuarioPermisoSupervision;
                })
                .collect(Collectors.toSet());
        usuario.setPermisosSupervision(nuevosPermisosSupervision);

        // Guardar cambios en la BD
        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public Usuario usuarioAgregar(UsuarioDTO usuarioDTO) {
        // Verificar si ya existe un usuario con el mismo nombre de login
        usuarioRepository.findByUsuarioUsuario(usuarioDTO.getUsuarioUsuario()).ifPresent(u -> {
            throw new IllegalStateException("El usuario ya existe: " + usuarioDTO.getUsuarioUsuario());
        });

        // Crear el servidor
        Servidor servidor = new Servidor();
        servidor.setIdServidor(Long.valueOf(1));

        // Crear y guardar la extensión automáticamente
        Extension extension = new Extension();
        extension.setExtensionUsername(usuarioDTO.getUsuarioUsuario()); // Asigna usuarioUsuario como username
        extension.setExtensionUri("sip:" + usuarioDTO.getUsuarioUsuario() + "@localhost");
        extension.setExtensionServer("ws://localhost:8088/ws");
        extension.setExtensionDominio("localhost");
        extension.setExtensionPassword(usuarioDTO.getUsuarioUsuario());
        extension.setExtensionServidor(servidor);
        Extension extensionGuardada = extensionRepository.save(extension); // Guardar la extensión y obtener ID

        // Defino el perfil
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(usuarioDTO.getUsuarioPerfil().getIdPerfil());

        // Defino la condicion
        Condicion condicion = new Condicion();
        condicion.setIdCondicion(usuarioDTO.getUsuarioCondicion().getIdCondicion());
        
        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsuarioNombre(usuarioDTO.getUsuarioNombre());
        usuario.setUsuarioApellido(usuarioDTO.getUsuarioApellido());
        usuario.setUsuarioUsuario(usuarioDTO.getUsuarioUsuario());
        usuario.setUsuarioCorreo(usuarioDTO.getUsuarioCorreo());
        usuario.setUsuarioExtension(extensionGuardada);
        usuario.setUsuarioPerfil(perfil);
        usuario.setUsuarioCondicion(condicion);

        // Guardar usuario en la DB
        usuarioRepository.save(usuario);
        return usuario;
    }
    
    @Transactional
    public void usuarioActualizar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + usuarioDTO.getIdUsuario()));

        // Hago consulta si modificó el usuarioUsuario
        if (!usuario.getUsuarioUsuario().equals(usuarioDTO.getUsuarioUsuario())) {
            // Si se modificó el usuarioUsuario debo modificarlo tambien en la extension
            Extension extension = extensionRepository.findById(usuario.getUsuarioExtension().getIdExtension())
                    .orElseThrow(() -> new EntityNotFoundException("Extension no encontrada con ID: " + usuarioDTO.getIdUsuario()));
            extension.setExtensionUsername(usuarioDTO.getUsuarioUsuario()); // Asigna usuarioUsuario como username
            extension.setExtensionUri("sip:" + usuarioDTO.getUsuarioUsuario() + "@localhost");
            extension.setExtensionPassword(usuarioDTO.getUsuarioUsuario());
            extensionRepository.save(extension);
        }

        // Actualizar datos
        usuario.setUsuarioNombre(usuarioDTO.getUsuarioNombre());
        usuario.setUsuarioApellido(usuarioDTO.getUsuarioApellido());
        usuario.setUsuarioUsuario(usuarioDTO.getUsuarioUsuario());
        usuario.setUsuarioCorreo(usuarioDTO.getUsuarioCorreo());

        // Defino el perfil
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(usuarioDTO.getUsuarioPerfil().getIdPerfil());
        
        usuario.setUsuarioPerfil(perfil);
        
        usuarioRepository.save(usuario);
    }
}
