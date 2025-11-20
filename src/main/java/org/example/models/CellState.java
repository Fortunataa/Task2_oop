package org.example.models;

public enum CellState {
    EMPTY("\uD83C\uDF0A"),
    SHIP("\uD83C\uDF0A"),
    MINE("\uD83D\uDCA3"),
    MISS("❌"),
    HIT("\uD83D\uDCA5"),
    DESTROYED("✴"),
    SUBMARINE("⚓"),
    MINESWEEPER("⛴");


    private final String symbol;

    CellState(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * Проверка на атаку клетки
     * @return true - если можно атаковать, false - если нельзя
     */
    public boolean canAttack() {
        return this == EMPTY || this == SHIP || this == MINE;
    }

    /**
     * Проверка на видимость противнику
     * @return true - если, MISS, HIT или DESTROYED, false - если EMPTY, SHIP или MINE
     */
    public boolean isVisible() {
        return this == MISS || this == HIT || this == DESTROYED;
    }



}
