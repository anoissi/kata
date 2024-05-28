package com.mowitnow.kata.domain.model;

import lombok.Getter;

@Getter
public class Lawn {
    private final int width;
    private final int height;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInside(Position position) {
        return position.getX() >= 0 && position.getX() <= width &&
                position.getY() >= 0 && position.getY() <= height;
    }
}