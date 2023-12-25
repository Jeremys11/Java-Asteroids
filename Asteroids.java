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
  private Bullet[] bullets;  // Declare the Bullets array

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
            int x = (int) (Math.random() * 30);
            int y = (int) (Math.random() * 30);
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

    // Initialize the Bullets array
    this.bullets = new Bullet[10];  // Change the size to the number of bullets you want
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

    // Draw the bullets
    for (Bullet bullet : bullets) {
        if (bullet != null) {
            bullet.paint(brush);
        }
    }

    // Update the bullets
    updateBullets();
}

public void updateBullets() {
    for (int i = 0; i < bullets.length; i++) {
        if (bullets[i] != null) {
            bullets[i].move();

            // Remove the bullet if it goes off screen
            if (bullets[i].position.x < 0 || bullets[i].position.x > width ||
                bullets[i].position.y < 0 || bullets[i].position.y > height) {
                bullets[i] = null;
                continue;
            }

            for (int j = 0; j < asteroids.length; j++) {
                if (asteroids[j] != null && bullets[i].overlaps(asteroids[j])) {
                    // Remove the bullet
                    bullets[i] = null;

                    // Create a new asteroid at a random position at the edge of the screen
                    Point[] shape = new Point[5];
                    for (int k = 0; k < shape.length; k++) {
                        int x = (int) (Math.random() * 30);
                        int y = (int) (Math.random() * 30);
                        shape[k] = new Point(x, y);
                    }
                    int positionX = (int) (Math.random() * width);
                    int positionY = (int) (Math.random() * height);
                    // Ensure the asteroid appears at the edge of the screen
                    if (Math.random() < 0.5) {
                        if (Math.random() < 0.5) {
                            positionX = 0;
                        } else {
                            positionX = width;
                        }
                    } else {
                        if (Math.random() < 0.5) {
                            positionY = 0;
                        } else {
                            positionY = height;
                        }
                    }
                    Point position = new Point(positionX, positionY);
                    double rotation = Math.random() * 360;
                    asteroids[j] = new Asteroid(shape, position, rotation);

                    break;
                }
            }
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
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Create a new bullet when the space bar is pressed
            for (int i = 0; i < bullets.length; i++) {
                if (bullets[i] == null) {
                    bullets[i] = ship.createBullet();
                    break;
                }
            }
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
                int x = (int) (Math.random() * 30);
                int y = (int) (Math.random() * 30);
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