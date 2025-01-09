package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.GrupoEstado;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoEstadoRepository extends JpaRepository<GrupoEstado, Long> {

    @Query("SELECT DISTINCT ge FROM GrupoEstado ge "
         + "LEFT JOIN FETCH ge.sector "
         + "LEFT JOIN FETCH ge.estados ges "
         + "LEFT JOIN FETCH ges.estado "
         + "WHERE ge.sector.idSector IN :sectorIds")
    Set<GrupoEstado> findGrupoEstadosBySectores(Set<Long> sectorIds);
}
