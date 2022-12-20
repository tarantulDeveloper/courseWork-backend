package kg.beaver.warehouse.repo;

import kg.beaver.warehouse.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Optional<Material> findByName(String name);
    Boolean existsByName(String name);
}
