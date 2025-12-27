package paint.model;

import java.awt.*;

public class RectShape extends ShapeBase {
	
    private int x, y, w, h; // Rectangle position (x,y) and dimensions (width, height)
    private boolean filled; // Flag: true = filled rectangle, false = outline only

    public RectShape(int x, int y, int w, int h,
                     Color color, Stroke stroke, boolean filled) {
        super(color, stroke);
        this.x = Math.min(x, x + w);  // Ensures x is leftmost coordinate
        this.y = Math.min(y, y + h); // Ensures y is topmost coordinate
        this.w = Math.abs(w);       // Width is always positive
        this.h = Math.abs(h);      // Height is always positive
        this.filled = filled;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(stroke);
        if (filled)
            g.fillRect(x, y, w, h);
        else
            g.drawRect(x, y, w, h);
    }
}

