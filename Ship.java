import java.awt.*;

class Ship extends Polygon {
    private int dx = 0;  // The ship's horizontal speed
    private int dy = 0;  // The ship's vertical speed

    public Ship(Point[] inShape, Point inPosition, double inRotation) {
        // Call the superclass constructor to create the polygon
        super(inShape, inPosition, inRotation);
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
    
    public void move(int dx, int dy) {
        System.out.println("Ship position: " + position.x + ", " + position.y);
        position.x += dx;
        // If the ship goes off the screen on the right, make it appear on the left
        if (position.x > 800) {
            position.x = 0;
        }
        // If the ship goes off the screen on the left, make it appear on the right
        else if (position.x < 0) {
            position.x = 800;
        }

        position.y += dy;
        // If the ship goes off the screen at the top, make it appear at the bottom
        if (position.y > 600) {
            position.y = 0;
        }
        // If the ship goes off the screen at the bottom, make it appear at the top
        else if (position.y < 0) {
            position.y = 600;
        }
    }

    public void stopHorizontalMovement() {
        dx = 0;
    }

    public void stopVerticalMovement() {
        dy = 0;
    }
}