package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		segments = new LinkedList<>();
		deltaX = MOVEMENT_SIZE;
		deltaY = 0;
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		for(int i = segments.size()-1; i>0; i--) {
			BodySegment a = segments.get(i-1);
			segments.get(i).setNewPosition(a.getX(), a.getY());
			
		}
			double x = segments.getFirst().getX() + deltaX;
			double y = segments.getFirst().getY() + deltaY;
			segments.getFirst().setNewPosition(x, y);
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for(BodySegment segment: segments) {
			segment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		double x = segments.getFirst().getX();
		double y = segments.getFirst().getY();
		double distance = Math.sqrt(Math.pow(x-f.getX(), 2)+Math.pow(y-f.getY(), 2));
		if(distance <= SEGMENT_SIZE) {
			BodySegment lastSegment = segments.getLast();
			segments.add(new BodySegment(lastSegment.getX(), lastSegment.getY(), SEGMENT_SIZE));
			return true;
		}

		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment a = segments.getFirst();
		return a.getX() >= 0 && a.getX() <= 1 && a.getY() >= 0 && a.getY() <= 1;
	}
}
