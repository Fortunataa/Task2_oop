package org.example.models.game_objects;

import org.example.models.Position;

import java.util.ArrayList;
import java.util.List;

public class Minesweeper extends Object {
    private final List<Position> allPositions;
    private boolean used;

    public Minesweeper(Position start) {
        this.allPositions = calculateHorizontalPositions(start);
        this.used = false;
    }

    /**
     * Вычисляет позицию минного тральщика
     * @param start начало минного тральщика
     * @return список клеток, которые занимает минный тральщик
     */
    public List<Position> calculateHorizontalPositions(Position start) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            positions.add(new Position(start.getX() + i, start.getY()));
        }

        return positions;
    }


}
