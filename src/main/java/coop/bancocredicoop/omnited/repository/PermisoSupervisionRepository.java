package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.PermisoSupervision;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoSupervisionRepository extends JpaRepository<PermisoSupervision, Long> {
    @Query("SELECT ps FROM PermisoSupervision ps")
    Set<PermisoSupervision> findAllPermisosSupervision();
}