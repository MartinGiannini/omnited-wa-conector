package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Estado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
    /**
     *
     * @param idEstado
     * @return
     */
    @Override
    Optional<Estado> findById(Long idEstado);
}