import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class Game extends Stage {

    Parent root;
    {
        try { root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("frontend/game.fxml"))); }
        catch (IOException | NullPointerException e) { Menu.exceptionHandler(e); }
    }
    Scene mainScene = new Scene(root);
    static Stage gameStage = new Stage();


    Game() {

        gameStage.setTitle("Nu, Pogodi!");
        gameStage.setScene(mainScene);
        gameStage.setOnCloseRequest( (WindowEvent e) -> {
            GameController.lives = 0;
        });
        gameStage.getIcons().add(Menu.MAIN_ICON);
        gameStage.setResizable(false);
        gameStage.show();

        root.requestFocus();

    }


}
