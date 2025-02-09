package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.UsuarioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioEstadoRepository extends JpaRepository<UsuarioEstado, Long> {
    
}