package kg.beaver.warehouse.repo;

import kg.beaver.warehouse.entities.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepo extends JpaRepository<Choice, Integer> {
}
