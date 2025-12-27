package paint.model;

import java.awt.*;

public class OvalShape extends ShapeBase {
    private int x, y, w, h;
    private boolean filled;

    public OvalShape(int x, int y, int w, int h,
                     Color color, Stroke stroke, boolean filled) {
        super(color, stroke);
        this.x = Math.min(x, x + w);
        this.y = Math.min(y, y + h);
        this.w = Math.abs(w);
        this.h = Math.abs(h);
        this.filled = filled;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(stroke);
        if (filled)
            g.fillOval(x, y, w, h);
        else
            g.drawOval(x, y, w, h);
    }
}


