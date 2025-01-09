package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.GrupoHabilidad;
import coop.bancocredicoop.omnited.entity.Sector;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoHabilidadRepository extends JpaRepository<Sector, Long> {

    @Query("SELECT DISTINCT gh FROM GrupoHabilidad gh "
            + "LEFT JOIN FETCH gh.habilidades ghh "
            + "LEFT JOIN FETCH ghh.habilidad h "
            + "LEFT JOIN FETCH gh.sector s "
            + "WHERE s.idSector IN :sectorIds")
    Set<GrupoHabilidad> findGrupoHabilidadesBySectores(Set<Long> sectorIds);
}
