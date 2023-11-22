/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/
import java.awt.*;
import java.awt.event.*;

class Asteroids extends Game implements KeyListener{
  private Ship ship;  // Declare the Ship instance

  public Asteroids() {
    super("Asteroids!",800,600);

    this.addKeyListener(this);  // Listen for key presses

    Point[] shipPoints = {new Point(0, -10), new Point(-10, 10), new Point(10, 10)};  // Define the ship's shape
    Point shipPosition = new Point(width / 2, height / 2);  // Set the ship's position
    this.ship = new Ship(shipPoints, shipPosition, 0);  // Initialize the Ship instance
  }
  
	public void paint(Graphics brush) {
    System.out.println("Asteroids.paint()");
    brush.setColor(Color.black);  //Color of the background
    brush.fillRect(0,0,width,height); //Color from top left to bottom right

    if (ship != null){
      ship.paint(brush);  // Draw the ship
    }
  }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Move the ship to the right
            ship.move(10, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Move the ship to the left
            ship.move(-10, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Move the ship up
            ship.move(0, -10);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Move the ship down
            ship.move(0, 10);
        }
        // Schedule a repaint
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Stop the ship's horizontal movement when the right or left arrow key is released
            ship.stopHorizontalMovement();
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Stop the ship's vertical movement when the up or down arrow key is released
            ship.stopVerticalMovement();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Leave this method blank
    }
  
	public static void main (String[] args) {
    Asteroids game = new Asteroids();
  }
}