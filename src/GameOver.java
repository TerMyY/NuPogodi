import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameOver extends Stage {

    Parent root;
    {
        try { root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("frontend/gameOver.fxml"))); }
        catch (IOException | NullPointerException e) { Menu.exceptionHandler(e); }
    }

    Scene mainScene = new Scene(root);
    Stage gameOverStage = new Stage();

    GameOver() {

        gameOverStage.setTitle("The end!");
        gameOverStage.setScene(mainScene);
        gameOverStage.getIcons().add(Menu.MAIN_ICON);
        gameOverStage.setResizable(false);
        gameOverStage.show();

    }

}
