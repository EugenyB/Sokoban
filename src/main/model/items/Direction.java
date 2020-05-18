package main.model.items;

public interface Direction {
    Direction UP = p -> new Pair(p.getRow()-1, p.getColumn());
    Direction DOWN = p -> new Pair(p.getRow()+1, p.getColumn());
    Direction LEFT = p -> new Pair(p.getRow(), p.getColumn()-1);
    Direction RIGHT = p -> new Pair(p.getRow(), p.getColumn()+1);

    Pair next(Pair position);
}
