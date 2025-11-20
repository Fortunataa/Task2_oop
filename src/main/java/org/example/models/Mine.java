package org.example.models;

public class Mine extends GameObject {
    private boolean activated;

    public Mine(Position position) {
        super(position, "MINE");
        this.activated = false;
    }

    /**
     * Проверяет, можно ли разместить мину
     * @param position позиция мины
     * @param board доска
     * @return true - если мина в пределах поля и клетка, на которую ставим, пустая. False - если нет.
     */
    @Override
    public boolean canBePlacedObject(Position position, GameBoard board) {
        return position.isInPlayingField(board.getSize()) && board.isPositionEmpty(position);
    }

    /**
     * Обработка попадания в мину
     */
    public void hittingMine() {
        if (!activated) {
            activated = true;
            destroyed = true;
            explode();
        }
    }

    private void explode() {
        System.out.println("💥 Мина взорвалась на позиции " + position);
    }

    public boolean isActivated() {
        return activated;
    }

    /**
     * Проверяет возможность активации мины
     * @return true - если мина не активирована и не сломана, false - если нет.
     */
    public boolean canActivate() {
        return !activated && !destroyed;
    }

    public String toString() {
        return String.format("Мина {позиция = %s, активирована = %s, уничтожена = %s}", position, activated, destroyed);
    }

}
