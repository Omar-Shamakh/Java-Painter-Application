package paint.model;

import java.awt.*;

// abstract parent class which all children inherit , it is a blueprint 

public abstract class ShapeBase {
    protected Color color;
    protected Stroke stroke;

    public ShapeBase(Color color, Stroke stroke) {
        this.color = color;
        this.stroke = stroke;
    }

    public abstract void draw(Graphics2D g2d);
}

