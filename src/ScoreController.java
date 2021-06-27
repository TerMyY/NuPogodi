import javafx.collections.ListChangeListener;
import javafx.css.CssParser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    public void back() { Menu.menuStage.setScene(Menu.scenes.get(0)); }
    public ListView<Label> listView = new ListView<Label>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CssParser.errorsProperty().addListener(new ListChangeListener<CssParser.ParseError>() {
            @Override
            public void onChanged(Change<? extends CssParser.ParseError> c) {
                Menu.exceptionHandler((null));
            }
        });

        try (BufferedReader input = new BufferedReader(new FileReader("scores.txt"))) {
            for (String s = input.readLine(); s != null; s = input.readLine())
                listView.getItems().add(new Label(s));
        }
        catch (IOException | NullPointerException e) { Menu.exceptionHandler(e); }

    }
}
