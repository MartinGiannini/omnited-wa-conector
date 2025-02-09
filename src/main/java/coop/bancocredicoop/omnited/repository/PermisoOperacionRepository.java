package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.PermisoOperacion;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoOperacionRepository extends JpaRepository<PermisoOperacion, Long> {
    @Query("SELECT po FROM PermisoOperacion po")
    Set<PermisoOperacion> findAllPermisosOperacion();
}