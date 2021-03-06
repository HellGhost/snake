package com.codenjoy.dojo.snake.model;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.model.artifacts.EmptySpace;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: oleksandr.baglai
 * Date: 3/10/13
 * Time: 2:51 PM
 */
public class SnakeDirectionTest {

    private Board board;
    private Snake snake;

    @Before
    public void setup() {
        board = mock(Board.class);
        when(board.getSize()).thenReturn(100);
        when(board.getAt(any(PointImpl.class))).thenReturn(new EmptySpace(new PointImpl(0, 0)));

        snake = new Snake(50, 50);
    }

    @Test
    public void shouldSnakeTailDirectionInvertedToHead_whenSnakeLengthIs2() {
        assertHeadAntTail(50, 50, Direction.RIGHT, Direction.LEFT);

        snakeUp();
        assertHeadAntTail(50, 51, Direction.UP, Direction.DOWN);

        snakeLeft();
        assertHeadAntTail(49, 51, Direction.LEFT, Direction.RIGHT);

        snakeDown();
        assertHeadAntTail(49, 50, Direction.DOWN, Direction.UP);

        snakeRight();
        assertHeadAntTail(50, 50, Direction.RIGHT, Direction.LEFT);
    }

    @Test
    public void shouldSnakeTailDirection_whenSnakeLengthIs3() {
        snakeGrow();
        assertHeadAntTail(51, 50, Direction.RIGHT, Direction.LEFT);

        snakeUp();
        assertHeadAntTail(51, 51, Direction.UP, Direction.LEFT);

        snakeLeft();
        assertHeadAntTail(50, 51, Direction.LEFT, Direction.DOWN);

        snakeDown();
        assertHeadAntTail(50, 50, Direction.DOWN, Direction.RIGHT);

        snakeRight();
        assertHeadAntTail(51, 50, Direction.RIGHT, Direction.UP);

        snakeWalk();
        assertHeadAntTail(52, 50, Direction.RIGHT, Direction.LEFT);
    }

    @Test
    public void shouldBodyDirection_whenSnakeLengthIs3_goCounterclockwise() {
        snakeGrow();
        assertBody(50, 50, BodyDirection.HORIZONTAL);

        snakeUp();
        assertBody(51, 50, BodyDirection.TURNED_LEFT_UP);

        snakeUp();
        assertBody(51, 51, BodyDirection.VERTICAL);

        snakeLeft();
        assertBody(51, 52, BodyDirection.TURNED_LEFT_DOWN);

        snakeLeft();
        assertBody(50, 52, BodyDirection.HORIZONTAL);

        snakeDown();
        assertBody(49, 52, BodyDirection.TURNED_RIGHT_DOWN);

        snakeDown();
        assertBody(49, 51, BodyDirection.VERTICAL);

        snakeRight();
        assertBody(49, 50, BodyDirection.TURNED_RIGHT_UP);

        snakeRight();
        assertBody(50, 50, BodyDirection.HORIZONTAL);
    }

    @Test
    public void shouldBodyDirection_whenSnakeLengthIs3_goClockwise() {
        snakeGrow();
        assertBody(50, 50, BodyDirection.HORIZONTAL);

        snakeDown();
        assertBody(51, 50, BodyDirection.TURNED_LEFT_DOWN);

        snakeDown();
        assertBody(51, 49, BodyDirection.VERTICAL);

        snakeLeft();
        assertBody(51, 48, BodyDirection.TURNED_LEFT_UP);

        snakeLeft();
        assertBody(50, 48, BodyDirection.HORIZONTAL);

        snakeUp();
        assertBody(49, 48, BodyDirection.TURNED_RIGHT_UP);

        snakeUp();
        assertBody(49, 49, BodyDirection.VERTICAL);

        snakeRight();
        assertBody(49, 50, BodyDirection.TURNED_RIGHT_DOWN);

        snakeRight();
        assertBody(50, 50, BodyDirection.HORIZONTAL);
    }

    @Test
    public void shouldSnakeIteratorStartsFromHead() {
        snakeGrow();

        Iterator<Point> iterator = snake.iterator();
        Point head = iterator.next();
        Point body = iterator.next();
        Point tail = iterator.next();

        assertTrue(snake.itsMyHead(head));
        assertTrue(snake.itsMyBody(body));
        assertTrue(snake.itsMyTail(tail));
    }


    private void snakeWalk() {
        snake.walk(board);
    }

    private void snakeGrow() {
        snake.grow();
        snakeWalk();
    }

    private void snakeRight() {
        snake.right();
        snakeWalk();
    }

    private void snakeDown() {
        snake.down();
        snakeWalk();
    }

    private void snakeLeft() {
        snake.left();
        snakeWalk();
    }

    private void snakeUp() {
        snake.up();
        snakeWalk();
    }

    private void assertHeadAntTail(int x, int y, Direction headDirection, Direction tailDirection) {
        assertEquals( "[headX, headY, headDirection, tailDirection]",
                asString(x, y, headDirection, tailDirection),

                asString(snake.getHead().getX(), snake.getHead().getY(),
                        snake.getDirection(), snake.getTailDirection()));
    }

    private void assertBody(int x, int y, BodyDirection bodyDirection) {
        Iterator<Point> iterator = snake.iterator();
        Point head = iterator.next();
        Point body = iterator.next();

        assertEquals( "[bodyX, bodyY, bodyDirection]",
                asString(x, y, bodyDirection),

                asString(body.getX(), body.getY(),
                        snake.getBodyDirection(body)));
    }

    private String asString(Object...args) {
        return Arrays.asList(args).toString();
    }
}
