package assignment9;

import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake snake;
	private Food food;
	private Food[] foodList;
	
	public Game() {
		StdDraw.setCanvasSize(600, 600);
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
		foodList = new Food[1];
		//FIXME - construct new Snake and Food objects
	}
	
	public void play() {
		int a = 0;
		while (a < 1 || a > 9) {
		    StdDraw.clear();
		    StdDraw.setPenColor(Color.BLACK);
		    StdDraw.text(0.5, 0.7, "SNAKE GAME 2");
		    StdDraw.text(0.5, 0.5, "Press 1–9 to pick the number of food");
		    StdDraw.show();

		    if (StdDraw.hasNextKeyTyped()) {
		        char key = StdDraw.nextKeyTyped();
		        if (key >= '1' && key <= '9') {
		            a = key - '0';
		        }
		    }

		    StdDraw.pause(50);
		}
		StdDraw.clear();
		foodList = new Food[a];
		for(int i = 0; i<a; i++) {
			food = new Food();
			foodList[i] = food;
		}
		while (snake.isInbounds()) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here			
			if(dir!=-1) {
				snake.changeDirection(dir);
			}
			snake.move();
			for(int i = 0; i<a; i++) {
				if(snake.eatFood(foodList[i])) {
					foodList[i] = new Food();
				}
			}
			updateDrawing();
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		//FIXME
		StdDraw.clear();
		snake.draw();
		for(int i = 0; i < foodList.length; i++) {
			foodList[i].draw();
		}
		StdDraw.pause(50);
		StdDraw.show();
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
