package game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameFieldSolutionStepRepository extends JpaRepository<GameFieldSolutionStepEntity, Long> {
}