package org.example.models;

public class Mine extends GameObject {
    private boolean activate;

    public Mine(Position position) {
        super(position, "MINE");
        this.activate = false;
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


}
