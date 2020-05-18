package main.model.items;

import java.util.Objects;

public class Pair {
    private final int row;
    private final int column;

    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row &&
                column == pair.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
