import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

public class Menu extends Application {

    static final Image MAIN_ICON = new Image("/res/icon.png");

    Parent menuRoot;
    Parent scoreRoot;
    {
        try
        {
            scoreRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("frontend/score.fxml")));
            menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("frontend/menu.fxml")));
        }
        catch (IOException | NullPointerException e) { exceptionHandler(e); }
    }

    Scene menuScene = new Scene(menuRoot);
    Scene scoreScene = new Scene(scoreRoot);
    static Vector<Scene> scenes = new Vector<Scene>();
    static Stage menuStage;
    static void exceptionHandler(Exception e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Oops... Something went wrong, try to reinstall the game if the problem won't go.\n" + e);
        alert.showAndWait();
        System.exit(-1);

    }

    @Override
    public void start(Stage primaryStage)

    {

        menuStage = primaryStage;
        menuStage.setTitle("Nu, Pogodi!");
        menuStage.setScene(menuScene);
        menuStage.getIcons().add(MAIN_ICON);
        menuStage.setResizable(false);
        scenes.add(menuScene);
        scenes.add(scoreScene);
        menuStage.show();

    }

    public static void main(String[] args) { launch(args); }

}
