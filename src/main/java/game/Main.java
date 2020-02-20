package game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GameConfiguration.class);
        FifteenGame game = context.getBean("fifteenGame", FifteenGame.class);
        game.run();
        game.printRepositories();
    }
}
