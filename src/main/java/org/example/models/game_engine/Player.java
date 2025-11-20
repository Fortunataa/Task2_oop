package org.example.models.game_engine;

import org.example.models.Position;
import org.example.models.game_objects.GameBoard;
import org.example.models.game_objects.GameObject;
import org.example.models.game_objects.Minesweeper;
import org.example.models.game_objects.Submarine;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final GameBoard board;
    private final List<Submarine> submarines;
    private final List<Minesweeper> minesweepers;

    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new GameBoard(boardSize);
        this.submarines = new ArrayList<>();
        this.minesweepers = new ArrayList<>();
    }


}
