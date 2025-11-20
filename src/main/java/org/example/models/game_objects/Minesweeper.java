package org.example.models.game_objects;

import org.example.models.Position;

import java.util.ArrayList;
import java.util.List;

public class Minesweeper extends GameObject {
    private final List<Position> allPositions;
    private boolean used;

    public Minesweeper(Position start) {
        super(start, "MINESWEEPER");
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

    /**
     * Проверяет, можно ли разместить минный тральщик
     * @param position позиция тральщика
     * @param board игровая доска
     * @return true - если можно разместить, false - если нельзя
     */
    @Override
    public boolean canBePlacedObject(Position position, GameBoard board) {
        List<Position> testPositions = calculateHorizontalPositions(position);

        for (Position pos : testPositions) {
            if (!pos.isInPlayingField(board.getSize())) {
                return false;
            }
        }

        Position lastPosition = testPositions.get(testPositions.size() - 1);
        return lastPosition.getX() == board.getSize() - 1;
    }

    @Override
    public boolean isDestroyed() {
        return used;
    }

    public List<Position> getAllPositions() {
        return new ArrayList<>(allPositions);
    }

    public boolean isUsed() {
        return used;
    }

    /**
     * Проверяет, можно ли использовать тральщик
     */
    public void markAsUsed() {
        this.used = true;
    }

    @Override
    public String toString() {
        return String.format("Тральщик [позиции = %s, использован = %s]", allPositions, used);
    }
}
