package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Sector;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("SELECT s FROM Sector s "
            + "LEFT JOIN FETCH s.estados e "
            + "LEFT JOIN FETCH s.habilidades h "
            + "LEFT JOIN FETCH s.gruposEstado ge "
            + "LEFT JOIN FETCH s.gruposHabilidad gh "
            + "WHERE s.idSector IN :sectorIds")
    Set<Sector> findByIdInWithDetails(Set<Long> sectorIds);
}
