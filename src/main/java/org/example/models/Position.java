package org.example.models;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Координаты не могу быть меньше 0: x = " + x + ", y = " + y);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /**
     * Проверяет, находится ли точка в поле координат
     * @param boardSize размер поля
     * @return true, если точка находится внутри поля
     */
    public boolean isInPlayingField(int boardSize) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    /**
     * Создает новую позицию со смещением
     * @param dx смещение по X
     * @param dy смещение по Y
     * @return новая позиция Position(x + dx, y + dy)
     */
    public Position createNewPosition(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }

    /**
     * Создает позицию слева от текущей
     * @return новая позиция Position(x - 1, y)
     */
    public Position left() {
        return new Position(x - 1, y);
    }

    /**
     * Создает позицию справа от текущей
     * @return новая позиция Position(x + 1, y)
     */
    public Position right() {
        return new Position(x + 1, y);
    }

    /**
     * Создает позицию сверху от текущей
     * @return новая позиция Position(x, y - 1)
     */
    public Position up() {
        return new Position(x, y - 1);
    }

    /**
     * Создает позицию снизу от текущей
     * @return новая позиция Position(x, y + 1)
     */
    public Position down() {
        return new Position(x, y + 1);
    }

}
