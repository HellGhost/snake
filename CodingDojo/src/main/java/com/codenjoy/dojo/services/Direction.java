package com.codenjoy.dojo.services;

import java.util.Random;

/**
 * User: oleksandr.baglai
 * Date: 3/8/13
 * Time: 10:35 PM
 */
public enum Direction {
    LEFT(0, -1, 0), RIGHT(1, 1, 0), UP(2, 0, -1), DOWN(3, 0, 1);

    private final int value;
    private final int dx;
    private final int dy;

    private Direction(int value, int dx, int dy) {
        this.value = value;
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction valueOf(int i) {
        for (Direction d : Direction.values()) {
            if (d.value == i) {
                return d;
            }
        }
        throw new IllegalArgumentException("No such Direction for " + i);
    }

    public int changeX(int x) {
        return x + dx;
    }

    public int changeY(int y) {
        return y + dy;
    }

    public Point change(Point point) {
        return new PointImpl(changeX(point.getX()),
                changeY(point.getX()));
    }

    public int getValue() {
        return value;
    }

    public Direction inverted() {
        switch (this) {
            case UP : return DOWN;
            case DOWN : return UP;
            case LEFT : return RIGHT;
            case RIGHT : return LEFT;
        }
        throw new IllegalArgumentException("Unsupported direction");
    }

    public static Direction random() {
        return Direction.valueOf(new Random().nextInt(4));
    }
}
