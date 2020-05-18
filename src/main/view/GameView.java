package main.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.model.Game;
import main.model.items.*;

import java.util.List;

public class GameView {
    private Game game;
    private Canvas canvas;

    public GameView(Game game, Canvas canvas) {
        this.game = game;
        this.canvas = canvas;
    }

    public void draw() {
        clear();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        List<Wall> walls = game.getWalls();
        walls.forEach(w->drawWall(w, gc));

        List<Target> targets = game.getTargets();
        targets.forEach(t->drawTarget(t, gc));

        List<Box> boxes = game.getBoxes();
        boxes.forEach(b->drawBox(b, gc));

        drawMan(game.getMan(), gc);
    }

    private void drawMan(Man man, GraphicsContext gc) {
        gc.setFill(Color.RED);
        int row = man.getRow();
        int column = man.getColumn();
        double cellSize = getCellSize();
        gc.fillOval(column*cellSize, row*cellSize, cellSize, cellSize);
        gc.strokeOval(column*cellSize, row*cellSize, cellSize, cellSize);
    }

    private double getCellSize() {
        double s1 = canvas.getWidth()/30;
        double s2 = canvas.getHeight()/22;
        return Math.min(s1,s2);
    }

    private void drawBox(Box b, GraphicsContext gc) {
        gc.setFill(new Color(0, 0, 1.0, 0.5));
        gc.setStroke(Color.BLACK);
        drawGameObject(b, gc);
    }

    private void drawGameObject(GameObject gameObject, GraphicsContext gc) {
        int row = gameObject.getRow();
        int column = gameObject.getColumn();
        double cellSize = getCellSize();
        gc.fillRect(column*cellSize, row*cellSize, cellSize, cellSize);
        gc.strokeRect(column*cellSize, row*cellSize, cellSize, cellSize);
    }

    private void drawTarget(Target t, GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);
        drawGameObject(t, gc);
    }

    private void drawWall(Wall w, GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET);
        gc.setStroke(Color.BLACK);
        drawGameObject(w, gc);
    }

    private void clear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
    }
}
