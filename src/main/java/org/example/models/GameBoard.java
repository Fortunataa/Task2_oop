package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int size;
    private final CellState[][] cells;
    private final List<GameObject> objects;

    public GameBoard(int size) {
        this.size = size;
        this.cells = new CellState[size][size];
        this.objects = new ArrayList<>();
    }
}
