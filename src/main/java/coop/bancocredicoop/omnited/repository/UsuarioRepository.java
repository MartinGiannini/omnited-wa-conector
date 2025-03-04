package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuarioUsuario(String usuario);

}
