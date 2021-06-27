import javafx.application.Platform;

import javafx.collections.ListChangeListener;
import javafx.css.CssParser;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    public GridPane gridPane = new GridPane();

    public void play(MouseEvent e)
    {

        Platform.runLater(Game::new);
        Menu.menuStage.hide();

    }

    public void score(MouseEvent e) { Menu.menuStage.setScene(Menu.scenes.get(1)); }

    public void exit(MouseEvent e) { Menu.menuStage.close(); }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CssParser.errorsProperty().addListener(new ListChangeListener<CssParser.ParseError>() {
            @Override
            public void onChanged(Change<? extends CssParser.ParseError> c) {
                Menu.exceptionHandler(null);
            }
        });
    }
}
