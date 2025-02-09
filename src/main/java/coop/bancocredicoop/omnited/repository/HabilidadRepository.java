package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {

    // Encuentra una habilidad por su ID
    @Override
    Optional<Habilidad> findById(Long idHabilidad);
}