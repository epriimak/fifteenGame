package game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameFieldSolutionStepRepository extends CrudRepository<GameFieldSolutionStepEntity, Long> {
}