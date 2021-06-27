import javafx.collections.ListChangeListener;
import javafx.css.CssParser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    public Label score = new Label();
    public TextField name = new TextField();
    ArrayList<Leader> leaders = new ArrayList<Leader>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CssParser.errorsProperty().addListener(new ListChangeListener<CssParser.ParseError>() {
            @Override
            public void onChanged(Change<? extends CssParser.ParseError> c) {
                Menu.exceptionHandler(null);
            }
        });
        score.setText("Score: " + GameController.score);

    }

    public void submit() {
        try (BufferedWriter input = new BufferedWriter(new FileWriter("scores.txt", true))) {
            input.append(new Leader(name.getText(), GameController.score).toString());
        } catch (IOException | NullPointerException e) {
            Menu.exceptionHandler(e);
        }
        Stage stage = (Stage) score.getScene().getWindow();
        stage.close();
    }
}
