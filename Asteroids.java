/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/
import java.awt.*;
import java.awt.event.*;

class Asteroids extends Game {
  private Ship ship;  // Declare the Ship instance

  public Asteroids() {
    super("Asteroids!",800,600);

    Point[] shipPoints = {new Point(0, -10), new Point(-10, 10), new Point(10, 10)};  // Define the ship's shape
    Point shipPosition = new Point(width / 2, height / 2);  // Set the ship's position
    this.ship = new Ship(shipPoints, shipPosition, 0);  // Initialize the Ship instance
  }
  
	public void paint(Graphics brush) {
    brush.setColor(Color.black);  //Color of the background
    brush.fillRect(0,0,width,height); //Color from top left to bottom right

    ship.paint(brush);  // Draw the ship
  }
  
	public static void main (String[] args) {
    new Asteroids();
  }
}