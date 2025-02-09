package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Sector;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("SELECT s FROM Sector s "
            + "LEFT JOIN FETCH s.sectorEstado e "
            + "LEFT JOIN FETCH s.sectorHabilidad h "
            + "LEFT JOIN FETCH s.grupoEstado ge "
            + "LEFT JOIN FETCH s.grupoHabilidad gh "
            + "WHERE s.idSector IN :sectorIds")
    Set<Sector> findByIdInWithDetails(Set<Long> sectorIds);
}
