/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> treeSet;

    public PointSET() {
        treeSet = new TreeSet<>();
    }

    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    public int size() {
        return treeSet.size();
    }

    public void insert(Point2D p) {
        validateArgument(p);
        treeSet.add(p);
    }

    public boolean contains(Point2D p) {
        validateArgument(p);
        return treeSet.contains(p);
    }

    public void draw() {
        for (Point2D p : treeSet) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        validateArgument(rect);
        TreeSet<Point2D> points = new TreeSet<>();

        for (Point2D current : treeSet) {
            double currentX = current.x();
            double currentY = current.y();

            if (currentX >= rect.xmin() && currentX <= rect.xmax() && currentY >= rect.ymin()
                    && currentY <= rect.ymax()) {
                points.add(current);
            }
        }

        return points;
    }

    public Point2D nearest(Point2D p) {
        validateArgument(p);
        Point2D nearestPoint = null;
        for (Point2D current : treeSet) {
            if (nearestPoint == null) {
                nearestPoint = current;
            }
            else {
                if (current.distanceTo(p) < nearestPoint.distanceTo(p)) {
                    nearestPoint = current;
                }
            }
        }
        return nearestPoint;
    }

    private void validateArgument(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
    }

    public static void main(String[] args) {

    }
}
