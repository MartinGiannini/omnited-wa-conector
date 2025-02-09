package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.UsuarioPermisoOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPermisoOperacionRepository extends JpaRepository<UsuarioPermisoOperacion, Long> {

}