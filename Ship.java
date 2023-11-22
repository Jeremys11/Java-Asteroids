import java.awt.*;

class Ship extends Polygon {

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
    public void move() {
    position.x += 1;

    // If the ship goes off the screen, make it appear on the other side
    if (position.x > 800) {
        position.x = 0;
    }
    }
}