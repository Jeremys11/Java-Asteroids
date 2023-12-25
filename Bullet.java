import java.awt.*;

public class Bullet extends Polygon {
    private Point velocity;

    public Bullet(Point[] shape, Point position, double rotation, Point velocity) {
        super(shape, position, rotation);
        this.velocity = velocity;
    }

    @Override
    public boolean contains(Point point) {
        // Implement this method based on your specific requirements
        return false;
    }

    @Override
    public boolean overlaps(Polygon polygon) {
        return polygon.contains(position);
    }

    public void paint(Graphics brush) {
        // Implement this method to paint the bullet
        // For example:
        brush.fillOval((int) position.x, (int) position.y, 5, 5);
    }

    public void move() {
        position.x += velocity.x;
        position.y += velocity.y;
    }
}