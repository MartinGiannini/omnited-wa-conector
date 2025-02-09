package coop.bancocredicoop.omnited.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import coop.bancocredicoop.omnited.entity.PermisoAdministracion;

@Repository
public interface PermisoAdministracionRepository extends JpaRepository<PermisoAdministracion, Long> {
    @Query("SELECT pa FROM PermisoAdministracion pa")
    Set<PermisoAdministracion> findAllPermisosAdministracion();
}