package org.example.models;

public abstract class GameObject {
    protected Position position;
    protected boolean destroyed;
    protected final String type;

    public GameObject(Position position, String type) {
        this.position = position;
        this.destroyed = false;
        this.type = type;
    }


}
