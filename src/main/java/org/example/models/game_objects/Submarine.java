package org.example.models.game_objects;

import org.example.models.Position;

import java.util.ArrayList;
import java.util.List;

public class Submarine extends GameObject {
    private final List<Position> allPositions;
    private boolean used;

    public Submarine(Position start) {
        super(start, "SUBMARINE");
        this.allPositions = calculateHorizontalPositions(start);
        this.used = false;

    }

    private List<Position> calculateHorizontalPositions(Position start) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            positions.add(new Position(start.getX() + i, start.getY()));
        }

        return positions;
    }

    @Override
    public boolean canBePlacedObject(Position position, GameBoard board) {
        List<Position> testPositions = calculateHorizontalPositions(position);

        for (Position pos : testPositions) {
            if (!pos.isInPlayingField(board.getSize())) {
                return false;
            }
        }
        return true;
    }
}
