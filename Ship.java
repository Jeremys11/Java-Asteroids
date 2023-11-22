import java.awt.*;

class Ship extends Polygon {

    public Ship(Point[] inShape, Point inPosition, double inRotation) {
        // Call the superclass constructor to create the polygon
        super(inShape, inPosition, inRotation);
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.white);  //Color of the ship
    }
}