package org.example.models.game_objects;

import org.example.models.GameBoard;
import org.example.models.Position;

import java.util.ArrayList;
import java.util.List;

public class Ship extends GameObject {
    private final int size;
    private final boolean vertical;
    private int health;
    private final List<Position> allPositions;

    /**
     * Создание корабля
     * @param size длина корабля
     * @param vertical true - если корабль вертикальный, false - если горизонтальный
     * @param startPosition "носик" корабля
     */
    public Ship(int size, boolean vertical, Position startPosition) {
        super(startPosition, "SHIP");
        this.size = size;
        this.vertical = vertical;
        this.health = size;
        this.allPositions = calculateAllPositions(startPosition, size);
    }

    /**
     * Считает список клеток, которые занимает корабль
     * @param start "носик" корабля
     * @param size длина корабля
     * @return список клеток, которые занимает корабль
     */
    private List<Position> calculateAllPositions(Position start, int size) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (isVertical()) {
                positions.add(new Position(start.getX(), start.getY() + i));
            } else {
                positions.add(new Position(start.getX() + i, start.getY()));
            }
        }
        return positions;
    }

    /**
     * Проверяет, можно ли разместить корабль на доске
     * @param position позиция корабля
     * @param board доска
     * @return true - если можно разместить, false - если нельзя
     */
    @Override
    public boolean canBePlacedObject(Position position, GameBoard board) {
        List<Position> testPosition = calculateAllPositions(position, size);

        for (Position pos :  testPosition) {
            if (!pos.isInPlayingField(board.getSize())) {
                return false;
            }

            if (!board.isPositionEmpty(position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Обрабатывает попадание в корабль
     */
    public void hittingInShip() {
        if (health > 0) {
            health--;
        }
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean isDestroyed() {
        return health <= 0;
    }

    public int getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }

    public List<Position> getAllPositions() {
        return allPositions;
    }

    /**
     * Получает позиции, которые не повреждены
     * @return если корабль уничтожен - возвращает пустой список, если нет - возвращает копию списка позиций корабля
     */
    public List<Position> getHealthyPositions() {
        return isDestroyed() ? new ArrayList<>() : new ArrayList<>(allPositions);
    }
}
