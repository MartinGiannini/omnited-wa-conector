package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.UsuarioHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioHabilidadRepository extends JpaRepository<UsuarioHabilidad, Long> {

}