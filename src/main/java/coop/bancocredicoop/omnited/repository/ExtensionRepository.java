package coop.bancocredicoop.omnited.repository;

import coop.bancocredicoop.omnited.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

}
