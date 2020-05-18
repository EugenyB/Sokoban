package main.model.items;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private List<Wall> walls;
    private List<Box> boxes;
    private List<Target> targets;
    private Man man;

    public Maze() {
        walls = new ArrayList<>();
        boxes = new ArrayList<>();
        targets = new ArrayList<>();
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public Man getMan() {
        return man;
    }

    public MoveResult moveMan(Direction dir) {
        Pair next = dir.next(man.getPosition());
        GameObject ob = new GameObject(next);
        if (containsObject(walls, ob)) {
            return MoveResult.IMPOSSIBLE;
        }
        Pair next2 = dir.next(next);
        GameObject ob2 = new GameObject(next2);
        if (containsObject(boxes, ob) && (containsObject(boxes, ob2) || containsObject(walls, ob2))) {
            return MoveResult.IMPOSSIBLE;
        }
        boxes.stream().filter(b->b.getPosition().equals(ob.getPosition())).findFirst().ifPresent(b->b.setPosition(next2));
        man.setPosition(next);
        boolean win = checkWin();
        if (win) return MoveResult.WIN;
        return MoveResult.SIMPLE;
    }

    private boolean checkWin() {
        if (boxes.stream().allMatch(b->containsObject(targets, b))) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Победа");
            alert.setHeaderText("Вы прошли уровень");
            alert.setContentText("С чем Вас и поздравляем!");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    private boolean containsObject(List<? extends GameObject> objects, GameObject ob) {
        return objects.stream().anyMatch(o -> o.getPosition().equals(ob.getPosition()));
    }
}
