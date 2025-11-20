package org.example.models.game_objects;

import org.example.models.CellState;
import org.example.models.Position;

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

    /**
     * Размещает игровой объект на доске
     * @param object объект для размещения
     * @return true - если объект размещен, false - если не получилось разместить
     */
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
        if (object instanceof Ship) {
            setCellState(position, CellState.SHIP);
        } else if (object instanceof Mine) {
            setCellState(position, CellState.MINE);
        } else if (object instanceof Submarine) {
            setCellState(position, CellState.SUBMARINE);
        } else if (object instanceof Minesweeper) {
            setCellState(position, CellState.MINESWEEPER);
        }
        return true;
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

    /**
     * Устанавливает состояние клетки в указанной позиции
     * @param position позиция клетки
     * @param state состояние
     */
    public void setCellState(Position position, CellState state) {
        if (!positionInTheField(position)) {
            throw new IllegalArgumentException("Позиция вне поля: " + position);
        }
        cells[position.getX()][position.getY()] = state;
    }

    /**
     * Получает объект в указанной позиции
     * @param position позиция для проверки
     * @return объект или null, есл клетка пуста
     */
    public GameObject getObject(Position position) {
        for (GameObject object : objects) {
            if (object.getPosition().equals(position)) {
                return object;
            }
        }
        return null;
    }

    /**
     * Получает список подводных лодок
     * @return список подводных лодок
     */
    public List<Submarine> getSubmarines() {
        List<Submarine> submarines = new ArrayList<>();
        for (GameObject obj : objects) {
            if (obj instanceof Submarine) {
                submarines.add((Submarine) obj);
            }
        }
        return submarines;
    }

    /**
     * Получает список кораблей
     * @return список кораблей
     */
    public List<Ship> getShips() {
        List<Ship> ships = new ArrayList<>();
        for (GameObject obj : objects) {
            if (obj instanceof Ship) {
                ships.add((Ship) obj);
            }
        }
        return ships;
    }

    /**
     * Получает список мин
     * @return список мин
     */
    public List<Mine> getMines() {
        List<Mine> mines = new ArrayList<>();
        for (GameObject obj : objects) {
            if (obj instanceof Mine) {
                mines.add((Mine) obj);
            }
        }
        return mines;
    }

    /**
     * Получает список минных тральщиков
     * @return список минных тральщиков
     */
    public List<Minesweeper> getMinesweepers() {
        List<Minesweeper> sweepers = new ArrayList<>();
        for (GameObject obj : objects) {
            if (obj instanceof Minesweeper) {
                sweepers.add((Minesweeper) obj);
            }
        }
        return sweepers;
    }

    /**
     * Проверяет, остались ли корабли на доске
     * @return true - если есть корабли на доске, false - если нет
     */
    public boolean hasActiveShips() {
        for (Ship ship : getShips()) {
            if (!ship.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    /**
     * Отображает поле в консоли
     */
    public void printBoard() {
        System.out.println("Игровое поле " + size + "⨯" + size + ":");
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.println(getCellState(new Position(x, y)).getSymbol() + " ");
            }
        }
        System.out.println();
    }
}
