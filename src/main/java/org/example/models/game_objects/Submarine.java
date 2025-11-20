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

    /**
     * Вычисляет позиции подводной лодки
     * @param start начало лодки, ее "носик"
     * @return список клеток, которые занимает подводная лодка
     */
    private List<Position> calculateHorizontalPositions(Position start) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            positions.add(new Position(start.getX() + i, start.getY()));
        }

        return positions;
    }

    /**
     * Проверяет, можно ли разместить подводную лодку
     * @param position позиция клетки
     * @param board игровая доска
     * @return true - если лодку можно разместить (она находится в поле), false - если нельзя (за пределами поля)
     */
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
     * Отмечает подлодку как использованную (самоуничтожение)
     */
    public void markAsUsed() {
        this.used = true;
    }

    /**
     * Проверяет, можно ли использовать подлодку
     */
    public boolean canBeUsed() {
        return !used;
    }

    @Override
    public String toString() {
        return String.format("Подлодка [позиции = %s, использована = %s]", allPositions, used);
    }
}
