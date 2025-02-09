package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Cola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaRepository extends JpaRepository<Cola, Long> {

}