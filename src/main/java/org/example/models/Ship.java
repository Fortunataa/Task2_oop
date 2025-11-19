package org.example.models;

import java.util.List;

public class Ship {
    private final int size;
    private final boolean vertical;
    private final int health;
    private final List<Position> allPositions;

    public Ship(int size, boolean vertical, Position startPosition) {
        this.size = size;
        this.vertical = vertical;
        this.health = size;

    }
}
