package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.GrupoHabilidad;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoHabilidadRepository extends JpaRepository<GrupoHabilidad, Long> {

    @Query("SELECT DISTINCT gh FROM GrupoHabilidad gh "
            + "LEFT JOIN FETCH gh.grupoHabilidadHabilidad ghh "
            + "LEFT JOIN FETCH ghh.habilidad h "
            + "LEFT JOIN FETCH gh.sector s "
            + "WHERE s.idSector IN :sectorIds")
    Set<GrupoHabilidad> findGrupoHabilidadesBySectores(Set<Long> sectorIds);

    @Modifying
    @Query("UPDATE GrupoHabilidad gh SET gh.grupoHabilidadNombre = :nombre WHERE gh.idGrupoHabilidad = :idGrupoHabilidad")
    void updateGrupoHabilidad(@Param("idGrupoHabilidad") Long idGrupoHabilidad, @Param("nombre") String nombre);

    @Modifying
    @Query("DELETE FROM GrupoHabilidadHabilidad ghh WHERE ghh.grupoHabilidad.idGrupoHabilidad = :idGrupoHabilidad")
    void deleteHabilidadesByGrupoHabilidad(@Param("idGrupoHabilidad") Long idGrupoHabilidad);
}