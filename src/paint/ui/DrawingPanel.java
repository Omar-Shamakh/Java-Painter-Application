package paint.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import paint.model.*;
import paint.tools.ToolType;

public class DrawingPanel extends JPanel {

    private ArrayList<ShapeBase> shapes = new ArrayList<>(); // Stores ALL drawn shapes
    private ShapeBase currentShape; // Reference to shape being drawn RIGHT NOW

    // default values
    private ToolType currentTool = ToolType.LINE;
    private Color currentColor = Color.BLACK;
    private boolean filled = false;
    private boolean dotted = false;

    private int startX, startY; // stores mouse press coordinates 

    
    // my constructor 
    public DrawingPanel() {
        setBackground(Color.WHITE);

        MouseAdapter mouseHandler = new MouseAdapter() { // Anonymous inner class

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();  // Get X coordinate of mouse press
                startY = e.getY(); // Get Y coordinate of mouse press

                
                
                // Handle pencil and eraser
                if (currentTool == ToolType.FREE_HAND ||
                    currentTool == ToolType.ERASER) {

                	Color c;
                	if (currentTool == ToolType.ERASER) {  // make the eraser just a white color like back ground 
                	    c = Color.WHITE;
                	} else {
                	    c = currentColor;
                	}
                    
                    currentShape = new FreeHandShape(
                            c, // Color black for pencil, white for eraser
                            new BasicStroke(10,
                                    BasicStroke.CAP_ROUND,     // Round line ends
                                    BasicStroke.JOIN_ROUND)); // Round line joins
                    ((FreeHandShape) currentShape).addPoint(e.getPoint()); // Add first point
                    shapes.add(currentShape); // Add to shapes list immediately
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape instanceof FreeHandShape) { // Type check
                    ((FreeHandShape) currentShape).addPoint(e.getPoint()); // Cast & add point
                    repaint(); // Request immediate redraw
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) { 
                int endX = e.getX(); // Get release coordinates
                int endY = e.getY();
                Stroke stroke = createStroke();

                switch (currentTool) {
                    case LINE:
                        shapes.add(new LineShape(
                                startX, startY, endX, endY,
                                currentColor, stroke));
                        break;
                    case RECTANGLE:
                        shapes.add(new RectShape(
                                startX, startY,
                                endX - startX, endY - startY,
                                currentColor, stroke, filled));
                        break;
                    case OVAL:
                        shapes.add(new OvalShape(
                                startX, startY,
                                endX - startX, endY - startY,
                                currentColor, stroke, filled));
                        break;
                }
                currentShape = null;   // Clear current shape reference
                repaint();   		  // Redraw with new shape
            }
        };

        addMouseListener(mouseHandler); 	   // Register for click events
        addMouseMotionListener(mouseHandler); // Register for drag events
    }

    // This is stroke creation helper and i searched for it 
    private Stroke createStroke() {
        if (dotted) {
            return new BasicStroke(
                    3,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL,
                    0,
                    new float[]{9},
                    0);
        }
        return new BasicStroke(3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (ShapeBase s : shapes)
            s.draw(g2);
    }

    // Setters
    public void setTool(ToolType t) { currentTool = t; }
    public void setColor(Color c) { currentColor = c; }
    public void setFilled(boolean f) { filled = f; }
    public void setDotted(boolean d) { dotted = d; }

    public void undo() {
        if (!shapes.isEmpty()) { // Check if there are shapes to undo
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    public void clear() {
        shapes.clear(); // Remove ALL shapes from list
        repaint();
    }

    // this is the bonus part , for me it was very challenging 
    public void saveImage() {
        try {
            BufferedImage img = new BufferedImage( // Create image buffer
                    getWidth(), getHeight(),      // Same size as panel
                    BufferedImage.TYPE_INT_RGB);
            paint(img.getGraphics()); 			// Draw current panel onto image

            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                ImageIO.write(img, "png", chooser.getSelectedFile()); // Save as PNG
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save Error");
        }
    }

    public void openImage() {
        try {
            JFileChooser chooser = new JFileChooser(); // File dialog
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                BufferedImage img = ImageIO.read(chooser.getSelectedFile()); // Read image
                Graphics g = getGraphics();
                g.drawImage(img, 0, 0, null); // Draw image at (0,0)
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Open Error");
        }
    }
}
