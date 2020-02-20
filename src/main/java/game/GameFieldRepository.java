package game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameFieldRepository extends CrudRepository<GameFieldEntity, Long> {
}