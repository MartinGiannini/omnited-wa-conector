package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.ColaHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaHabilidadRepository extends JpaRepository<ColaHabilidad, Long> {

    @Modifying
    @Query("DELETE FROM ColaHabilidad ch WHERE ch.cola.idCola = :idCola")
    void deleteByColaId(@Param("idCola") Long idCola);
}