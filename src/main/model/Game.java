package main.model;

import main.model.items.*;
import main.util.Level;
import main.util.Levels;

import java.util.List;


public class Game {
    private final Level currentLevel;

    public Game(int level) {
        currentLevel = Levels.getLevel(level);
        currentLevel.buildMaze();
    }

    public Game() {
        this(1);
    }

    public List<Wall> getWalls() {
        return currentLevel.getStoredMaze().getWalls();
    }

    public List<Box> getBoxes() {
        return currentLevel.getStoredMaze().getBoxes();
    }

    public List<Target> getTargets() {
        return currentLevel.getStoredMaze().getTargets();
    }

    public Man getMan() {
        return currentLevel.getStoredMaze().getMan();
    }

    public MoveResult move(Direction direction) {
        return currentLevel.getStoredMaze().moveMan(direction);
    }
}
