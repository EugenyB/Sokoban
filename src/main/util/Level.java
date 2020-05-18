package main.util;


import main.Main;
import main.model.items.*;

import java.util.List;

public class Level {
    private final int sizeX;
    private final int sizeY;
    private final int length;
    private final char[][] data;
    private final int num;
    private Maze storedMaze;

    public Level(int num, int sizeX, int sizeY, int length, char[][] data) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.length = length;
        this.data = data;
        this.num = num;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getLength() {
        return length;
    }

    public int getNum() {
        return num;
    }

    public void buildMaze() {
        Maze maze = new Maze();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                switch (data[i][j]) {
                    case 'X': maze.getWalls().add(new Wall(new Pair(i,j)));
                        break;
                    case '*': maze.getBoxes().add(new Box(new Pair(i,j)));
                        break;
                    case '.': maze.getTargets().add(new Target(new Pair(i,j)));
                        break;
                    case '&': maze.getBoxes().add(new Box(new Pair(i,j)));
                              maze.getTargets().add(new Target(new Pair(i,j)));
                              break;
                    case '@': maze.setMan(new Man(new Pair(i,j)));
                        break;
                }
            }
        }
        storedMaze = maze;
    }

    public Maze getStoredMaze() {
        return storedMaze;
    }
}

