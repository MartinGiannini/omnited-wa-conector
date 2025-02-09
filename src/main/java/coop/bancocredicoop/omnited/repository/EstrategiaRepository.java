package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Estrategia;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstrategiaRepository extends JpaRepository<Estrategia, Long> {

    // Método para obtener todas las estrategias
    @Query("SELECT e FROM Estrategia e")
    Set<Estrategia> findAllEstrategias();
}