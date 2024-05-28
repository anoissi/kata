package com.mowitnow.kata.domain.model;

import lombok.Getter;

@Getter
public class Mower {
    private Position position;
    private Direction direction;

    public Mower(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void execute(char command, Lawn lawn) {
        switch (command) {
            case 'G':
                direction = direction.turnLeft();
                break;
            case 'D':
                direction = direction.turnRight();
                break;
            case 'A':
                Position newPosition = new Position(position.getX(), position.getY());
                newPosition.move(direction);
                if (lawn.isInside(newPosition)) {
                    position = newPosition;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }
    }
}