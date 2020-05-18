package main.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import main.model.Game;
import main.model.items.Direction;
import main.model.items.MoveResult;
import main.util.Levels;
import main.view.GameView;

public class Controller {
    private Game game;
    private GameView gv;

    @FXML private Pane pane;

    @FXML private Canvas canvas;

    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.widthProperty().addListener(e->gv.draw());
        canvas.heightProperty().addListener(e->gv.draw());

        game = new Game();
        gv = new GameView(game, canvas);
    }

    public void processKey(KeyEvent keyEvent) {
        MoveResult res = MoveResult.IMPOSSIBLE;
        switch (keyEvent.getCode()) {
            case UP: res = game.move(Direction.UP);
                break;
            case DOWN: res = game.move(Direction.DOWN);
                break;
            case LEFT: res = game.move(Direction.LEFT);
                break;
            case RIGHT: res = game.move(Direction.RIGHT);
                break;
        }
        if (res==MoveResult.WIN) {
            if (Levels.getCurrentLevel()==Levels.getTotal()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Победа");
                alert.setHeaderText("Вы прошли все уровни");
                alert.setContentText("С чем Вас и поздравляем!");
                alert.showAndWait();
                Platform.exit();
            }
            game = new Game(Levels.getCurrentLevel()+1);
            gv = new GameView(game, canvas);
        }
        gv.draw();
    }

}

