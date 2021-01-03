/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("element cannot be null");
            }
        }

        Point[] clonedPoints = points.clone();
        Arrays.sort(clonedPoints);
        validate(clonedPoints);

        final int n = points.length;
        List<LineSegment> list = new LinkedList<>();

        for (int i = 0; i < n - 3; i++) {
            Point pointI = clonedPoints[i];

            for (int j = i + 1; j < n - 2; j++) {
                Point pointJ = clonedPoints[j];

                double slopeIJ = pointI.slopeTo(pointJ);

                for (int k = j + 1; k < n - 1; k++) {
                    Point pointK = clonedPoints[k];
                    double slopeIK = pointI.slopeTo(pointK);
                    if (slopeIJ == slopeIK) {
                        for (int m = k + 1; m < n; m++) {
                            Point pointM = clonedPoints[m];
                            double slopeIM = pointM.slopeTo(pointM);
                            if (slopeIJ == slopeIM) {
                                list.add(new LineSegment(pointI, pointM));
                            }
                        }
                    }
                }
            }
        }

        lineSegments = list.toArray(new LineSegment[0]);
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    private void validate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("duplicates found");
            }
        }
    }
}
