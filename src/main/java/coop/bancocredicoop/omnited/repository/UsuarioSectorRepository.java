package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.UsuarioSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSectorRepository extends JpaRepository<UsuarioSector, Long> {

    @Modifying
    @Query("DELETE FROM UsuarioSector us WHERE us.usuario.idUsuario = :idUsuario")
    void deleteByUsuarioId(@Param("idUsuario") Long idUsuario);
}
