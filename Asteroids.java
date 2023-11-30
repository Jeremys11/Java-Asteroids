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
  private Asteroid[] asteroids;  // Declare the Asteroids array

  public Asteroids() {
    super("Asteroids!",800,600);

    this.addKeyListener(this);  // Listen for key presses

    Point[] shipPoints = {new Point(0, -20), new Point(-10, 20), new Point(10, 20)};  // Define the ship's shape
    Point shipPosition = new Point(width / 2, height / 2);  // Set the ship's position
    this.ship = new Ship(shipPoints, shipPosition, 0);  // Initialize the Ship instance

    // Initialize the Asteroids array
    this.asteroids = new Asteroid[10];  // Change the size to the number of asteroids you want
    for (int i = 0; i < this.asteroids.length; i++) {
        // Generate a random shape with 5 points within a 10x10 pixel area
        Point[] shape = new Point[5];
        for (int j = 0; j < shape.length; j++) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            shape[j] = new Point(x, y);
        }

        // Generate a random position at the edge of the screen
        int positionX = (int) (Math.random() * width);
        int positionY = (int) (Math.random() * height);
        Point position = new Point(positionX, positionY);

        // Generate a random rotation
        double rotation = Math.random() * 360;

        // Initialize the Asteroid instance
        this.asteroids[i] = new Asteroid(shape, position, rotation);
    }
  }
  
	public void paint(Graphics brush) {
    System.out.println("Asteroids.paint()");
    brush.setColor(Color.black);  //Color of the background
    brush.fillRect(0,0,width,height); //Color from top left to bottom right

    // Draw the ship
    if (ship != null){
      ship.paint(brush); 
    }

    // Draw and move the asteroids
    for (Asteroid asteroid : asteroids) {
        asteroid.paint(brush);
        asteroid.move();

        // Check if the asteroid intersects with the ship
        if (asteroid.overlaps(ship)) {
            // Reset the game state
            resetGameState();
            break;
        }
    }
  }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Move the ship to the right
            ship.move(20, 0, 90);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Move the ship to the left
            ship.move(-20, 0, 270);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Move the ship up
            ship.move(0, -20, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Move the ship down
            ship.move(0, 20, 180);
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

  private void resetGameState() {
        // Reset the ship
        Point[] shipPoints = {new Point(0, -20), new Point(-10, 20), new Point(10, 20)};
        Point shipPosition = new Point(width / 2, height / 2);
        this.ship = new Ship(shipPoints, shipPosition, 0);

        // Reset the asteroids
        for (int i = 0; i < this.asteroids.length; i++) {
            // Generate a new random shape, position, and rotation for the asteroid
            // This is the same code as in the constructor
            Point[] shape = new Point[5];
            for (int j = 0; j < shape.length; j++) {
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);
                shape[j] = new Point(x, y);
            }

            int positionX = (int) (Math.random() * width);
            int positionY = (int) (Math.random() * height);
            Point position = new Point(positionX, positionY);

            double rotation = Math.random() * 360;

            this.asteroids[i] = new Asteroid(shape, position, rotation);
        }
    }
}