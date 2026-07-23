package ec.edu.ups.icc.labevaluation.supplies.repositories;

import ec.edu.ups.icc.labevaluation.supplies.entities.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepository extends JpaRepository<SupplyEntity, Long> {
    boolean existsByNameIgnoreCaseAndDeletedFalse(String name);
}