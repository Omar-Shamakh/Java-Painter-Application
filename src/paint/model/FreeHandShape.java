package paint.model;

import java.awt.*;
import java.util.ArrayList;

public class FreeHandShape extends ShapeBase {
    private ArrayList<Point> points = new ArrayList<>();
    // Stores all points in array list

    public FreeHandShape(Color color, Stroke stroke) {
        super(color, stroke);
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(stroke);
        for (int i = 0; i < points.size() - 1; i++) {  // points.size()-1 because we need pairs
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y); // Connect them with a line
        }
    }
}

