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
        initializeBoard();
    }


    /**
     * Получает все объекты на поле
     * @return список всех объектов
     */
    public List<GameObject> getObjects() {
        return new ArrayList<>(objects);
    }



    /**
     * Создает начальную(пустую) доску
     */
    private void initializeBoard() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y] = CellState.EMPTY;
            }
        }
    }

    public boolean placeAnObject(GameObject object) {
        Position position = object.getPosition();

        if (!position.isInPlayingField(size)) {
            return false;
        }

        if (!object.canBePlacedObject(position, this)) {
            return false;
        }

        if (!isPositionEmpty(position)) {
            return false;
        }

        objects.add(object);
    }

    /**
     * Проверяет пустая ли клетка
     * @param position позиция клетки
     * @return true - если клетка пустая, false - если нет
     */
    public boolean isPositionEmpty(Position position) {
        if (!positionInTheField(position)) {
            return false;
        }
        return cells[position.getX()][position.getY()] == CellState.EMPTY;
    }


    /**
     * Проверяет, находится ли позиция в поле
     * @param position позиция объекта
     * @return true, если позиция находится внутри поля
     */
    public boolean positionInTheField(Position position) {
        return position.isInPlayingField(size);
    }


    /**
     * Получает состояние клетки в указанной позиции
     * @param position позиция объекта
     * @return состояние клетки
     */
    public CellState getCellState(Position position) {
        if (!positionInTheField(position)) {
            throw new IllegalArgumentException("Позиция вне поля: " + position);
        }
        return cells[position.getX()][position.getY()];
    }


}
