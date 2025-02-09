package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.GrupoEstadoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GrupoEstadoEstadoRepository extends JpaRepository<GrupoEstadoEstado, Long> {
    
    @Modifying
    @Query("DELETE FROM GrupoEstadoEstado ge WHERE ge.grupoEstado.idGrupoEstado = :idGrupoEstado")
    void deleteEstadosByGrupoEstado(@Param("idGrupoEstado") Long idGrupoEstado);
}
