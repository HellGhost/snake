package com.globallogic.snake;

public class Snake {

	private int x;
	private int y;
	private String direction;

	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		direction = "right";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLength() {
		return 2;
	}

	public String getDirection() {
		return direction;
	}

	// методы turn - это методы, который может вызывать игрок, 
	// а вот move  - вызывает сама доска (игра) когда проходит один такт, 
	// именно по этому мы их деалем невидимыми извне пакета  
	void moveRight() { 
		x++;
	}

	public void turnDown() {
		direction = "down";
	}

	void moveDown() {
		y++;
	}

	public void turnUp() {
		direction = "up";
	}

	public void moveUp() {
		y--;
	}

}