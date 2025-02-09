package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.GrupoHabilidadHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoHabilidadHabilidadRepository extends JpaRepository<GrupoHabilidadHabilidad, Long> {

    @Modifying
    @Query("DELETE FROM GrupoHabilidadHabilidad ghh WHERE ghh.grupoHabilidad.idGrupoHabilidad = :idGrupoHabilidad")
    void deleteByGrupoHabilidadId(@Param("idGrupoHabilidad") Long idGrupoHabilidad);
}