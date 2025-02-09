package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.UsuarioPermisoSupervision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPermisoSupervisionRepository extends JpaRepository<UsuarioPermisoSupervision, Long> {

}