import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.css.CssParser;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    int tickRate = 1950;
    static int lives = 4;
    static int score = 0;
    public GridPane gridPane = new GridPane();
    public ImageView wolf1 = new ImageView("/res/wolf1.png");
    public ImageView wolf2 = new ImageView("/res/wolf2.png");
    public ImageView wolf3 = new ImageView("/res/wolf3.png");
    public ImageView wolf4 = new ImageView("/res/wolf4.png");
    public Label scoreLabel = new Label();
    public ImageView hp1 = new ImageView("/res/hp.png");
    public ImageView hp2 = new ImageView("/res/hp.png");
    public ImageView hp3 = new ImageView("/res/hp.png");
    public ImageView hp4 = new ImageView("/res/hp.png");
    ImageView egg1;
    ImageView egg2;
    ImageView egg3;
    ImageView egg4;
    ImageView egg5;
    ImageView chick1;
    ImageView chick2;
    ImageView chick3;
    ImageView chick4;
    ImageView chick5;
    enum Direction
    {
        LEFT,
        LEFT_DOWN,
        RIGHT,
        RIGHT_DOWN
    }
    Direction wolfDirection = Direction.LEFT;
    void egg() {

        Direction eggDirection = Direction.LEFT;
        int random = (int) (Math.random() * 4 + 1);
        switch (random)

        {

            case 1 ->

                    {

                        egg1 = new ImageView("/res/eggL1.png");
                        egg2 = new ImageView("/res/eggL2.png");
                        egg3 = new ImageView("/res/eggL3.png");
                        egg4 = new ImageView("/res/eggL4.png");
                        egg5 = new ImageView("/res/eggL5.png");

                    }

            case 2 ->

                    {

                        eggDirection = Direction.LEFT_DOWN;
                        egg1 = new ImageView("/res/eggLD1.png");
                        egg2 = new ImageView("/res/eggLD2.png");
                        egg3 = new ImageView("/res/eggLD3.png");
                        egg4 = new ImageView("/res/eggLD4.png");
                        egg5 = new ImageView("/res/eggLD5.png");

                    }

            case 3 ->

                    {

                        eggDirection = Direction.RIGHT;
                        egg1 = new ImageView("/res/eggR1.png");
                        egg2 = new ImageView("/res/eggR2.png");
                        egg3 = new ImageView("/res/eggR3.png");
                        egg4 = new ImageView("/res/eggR4.png");
                        egg5 = new ImageView("/res/eggR5.png");

                    }

            case 4 ->

                    {

                        eggDirection = Direction.RIGHT_DOWN;
                        egg1 = new ImageView("/res/eggRD1.png");
                        egg2 = new ImageView("/res/eggRD2.png");
                        egg3 = new ImageView("/res/eggRD3.png");
                        egg4 = new ImageView("/res/eggRD4.png");
                        egg5 = new ImageView("/res/eggRD5.png");

                    }

        }
        gridPane.getChildren().add(animateEggs(egg1, egg2, egg3, egg4, egg5, eggDirection));
    }

    private Group animateEggs(ImageView egg1, ImageView egg2, ImageView egg3, ImageView egg4, ImageView egg5, Direction eggDirection) {

        Timeline timeline = new Timeline();
        Group eggs = new Group();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (ActionEvent e) -> { eggs.getChildren().setAll(egg1); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), (ActionEvent e) -> { eggs.getChildren().setAll(egg2); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3000), (ActionEvent e) -> { eggs.getChildren().setAll(egg3); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000), (ActionEvent e) -> { eggs.getChildren().setAll(egg4); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), (ActionEvent e) -> { eggs.getChildren().setAll(egg5); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6000), (ActionEvent e) -> { eggs.getChildren().setAll(egg5); }));
        timeline.setOnFinished((ActionEvent e) -> {
            timeline.stop();
            eggs.setVisible(false);
            if(wolfDirection == eggDirection && scoreTimer.isRunning())
                score++;
            else if(wolfDirection != eggDirection && scoreTimer.isRunning()) {
                lives--;
                chick(eggDirection);
            }

        });

        timeline.play();
        return eggs;

    }

    void chick(Direction chicksDirection) {

        if(chicksDirection == Direction.LEFT || chicksDirection == Direction.LEFT_DOWN) {
            chick1 = new ImageView("/res/chickL1.png");
            chick2 = new ImageView("/res/chickL2.png");
            chick3 = new ImageView("/res/chickL3.png");
            chick4 = new ImageView("/res/chickL4.png");
            chick5 = new ImageView("/res/chickL5.png");
        }
        else if(chicksDirection == Direction.RIGHT || chicksDirection == Direction.RIGHT_DOWN) {
            chick1 = new ImageView("/res/chickR1.png");
            chick2 = new ImageView("/res/chickR2.png");
            chick3 = new ImageView("/res/chickR3.png");
            chick4 = new ImageView("/res/chickR4.png");
            chick5 = new ImageView("/res/chickR5.png");
        }
        gridPane.getChildren().add(animateChicks(chick1, chick2, chick3, chick4, chick5));

    }

    private Group animateChicks(ImageView chick1, ImageView chick2, ImageView chick3, ImageView chick4, ImageView chick5) {

        Timeline timeline = new Timeline();
        Group chicks = new Group();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent e) -> { chicks.getChildren().setAll(chick1); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (ActionEvent e) -> { chicks.getChildren().setAll(chick2); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1500), (ActionEvent e) -> { chicks.getChildren().setAll(chick3); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), (ActionEvent e) -> { chicks.getChildren().setAll(chick4); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), (ActionEvent e) -> { chicks.getChildren().setAll(chick5); }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3000), (ActionEvent e) -> { chicks.getChildren().setAll(chick5); }));
        timeline.play();
        timeline.setOnFinished((ActionEvent e) -> { timeline.stop(); chicks.setVisible(false); });
        return chicks;
    }

    ActionListener makeScore = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Platform.runLater(() -> {
                scoreLabel.setText(String.valueOf(score));
                hp1.setVisible(lives >= 1);
                hp2.setVisible(lives >= 2);
                hp3.setVisible(lives >= 3);
                hp4.setVisible(lives == 4);
                if(score > 10 && score < 25) {

                    speed1.stop();
                    speed2.start();

                }
                else if(score > 25 && score < 40) {

                    speed2.stop();
                    speed3.start();

                }
                else if(score > 40 && score < 55) {

                    speed3.stop();
                    speed4.start();

                }
                else if(score > 55 && score < 75) {

                    speed4.stop();
                    speed5.start();

                }

            } );
        }
    };

    ActionListener makeEggs = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Platform.runLater(() -> egg() );
        }
    };

    Timer speed1 = new Timer(tickRate, makeEggs);
    Timer speed2 = new Timer(tickRate - 155, makeEggs);
    Timer speed3 = new Timer(tickRate - 355, makeEggs);
    Timer speed4 = new Timer(tickRate - 555, makeEggs);
    Timer speed5 = new Timer(tickRate - 595, makeEggs);
    Timer scoreTimer = new Timer(100,makeScore);

    public void move(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case Q ->
            {
                wolfDirection = Direction.LEFT;
                wolf1.setVisible(true);
                wolf2.setVisible(false);
                wolf3.setVisible(false);
                wolf4.setVisible(false);
                if(keyEvent.isControlDown() && keyEvent.isShiftDown())
                    lives = 0;
            }
            case E ->
            {
                wolfDirection = Direction.RIGHT;
                wolf2.setVisible(true);
                wolf1.setVisible(false);
                wolf3.setVisible(false);
                wolf4.setVisible(false);
            }
            case A ->
            {
                wolfDirection = Direction.LEFT_DOWN;
                wolf3.setVisible(true);
                wolf1.setVisible(false);
                wolf2.setVisible(false);
                wolf4.setVisible(false);
            }
            case D ->
            {
                wolfDirection = Direction.RIGHT_DOWN;
                wolf4.setVisible(true);
                wolf1.setVisible(false);
                wolf3.setVisible(false);
                wolf2.setVisible(false);
            }

        }

    }

    public void moveButton(MouseEvent e) {

        Button button = (Button) e.getSource();
        Robot robot = new Robot();

        switch (button.getText())
        {

            case "Q" -> { robot.keyPress(KeyCode.Q); }
            case "E" -> { robot.keyPress(KeyCode.E); }
            case "A" -> { robot.keyPress(KeyCode.A); }
            case "D" -> { robot.keyPress(KeyCode.D); }

        }

    }

    void stopTimers() {

        speed1.stop();
        speed2.stop();
        speed3.stop();
        speed4.stop();
        speed5.stop();
        scoreTimer.stop();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hp1.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    stopTimers();
                    Stage gameStage = (Stage) gridPane.getScene().getWindow();
                    gameStage.close();
                    Platform.runLater(GameOver::new);
                    Menu.menuStage.show();
                }
            }
        });
        CssParser.errorsProperty().addListener(new ListChangeListener<CssParser.ParseError>() {
            @Override
            public void onChanged(Change<? extends CssParser.ParseError> c) {
                Menu.exceptionHandler(null);
            }
        });
        lives = 4;
        score = 0;
        speed1.start();
        scoreTimer.start();

    }


}
