package game;

import game.local.GameField;
import game.local.GameFieldException;
import game.local.Solver;
import game.local.SolverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Component
//@Service
public class FifteenGame {

    FifteenGame(){ System.out.println("FifteenGame");}

    //@Autowired                так как это прописано уже в .properties
    //DataSource dataSource;

    @Autowired
    GameFieldRepository gameFieldRepository;

    @Autowired
    GameFieldSolutionStepRepository gameFieldSolutionStepRepository;

    @Bean
    public void run() {
        Optional<GameFieldEntity> optionalField = gameFieldRepository.findById(1L);
        String field = null;

        if (optionalField.isPresent())
            field = optionalField.get().field;

        try {
            GameField gameField = new GameField(field);
            Solver solver = new Solver(gameField);

            List<GameFieldSolutionStepEntity> solutionStepList = solver.getSolutionStepEntities();
            solutionStepList.forEach(solutionStep
                    -> gameFieldSolutionStepRepository.save(solutionStep));
        } catch (GameFieldException | SolverException e) {
            GameFieldSolutionStepEntity gameFieldSolutionEntity = new GameFieldSolutionStepEntity(
                    "not_found\n", "not_found");
            gameFieldSolutionStepRepository.save(gameFieldSolutionEntity);
        }
    }

    @Bean
    public void printRepositories() {
        Iterable<GameFieldEntity> gameFieldRepositoryAll =
                gameFieldRepository.findAll();
        gameFieldRepositoryAll.forEach(field
                -> System.out.println(field.toString()));

        Iterable<GameFieldSolutionStepEntity> gameFieldSolutionRepositoryAll =
                gameFieldSolutionStepRepository.findAll();
        gameFieldSolutionRepositoryAll.forEach(fieldStepSolution
                -> System.out.println(fieldStepSolution.toString()));
    }
}
