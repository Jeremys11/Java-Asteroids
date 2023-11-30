import java.awt.*;

public class Asteroid extends Polygon {
    private double velocityX;  // The velocity in the x direction
    private double velocityY;  // The velocity in the y direction

    public Asteroid(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);

        // Initialize the velocities with random values
        this.velocityX = Math.random() * 10 - 5;  // Random value between -5 and 5
        this.velocityY = Math.random() * 10 - 5;  // Random value between -5 and 5
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.white);  //Color of the ship

        // Get the points of the polygon
        Point[] points = getPoints();

        // Create arrays for the x and y values
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        // Fill the arrays with the x and y values of the points
        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }

        // Draw the polygon
        brush.drawPolygon(xPoints, yPoints, points.length);
    }

    public void move() {
        position.x += velocityX;
        position.y += velocityY;
    
        // Handle the rollover
        if (position.x > 800) {
            position.x -= 800;
        } else if (position.x < 0) {
            position.x += 800;
        }
    
        if (position.y > 600) {
            position.y -= 600;
        } else if (position.y < 0) {
            position.y += 600;
        }
    }
}