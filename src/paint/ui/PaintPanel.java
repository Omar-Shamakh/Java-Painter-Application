package paint.ui;

import javax.swing.*;
import java.awt.*;
import paint.tools.ToolType;

public class PaintPanel extends JPanel {

    public PaintPanel() {
        setLayout(new BorderLayout());

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        
        toolbar.add(new JLabel("Functions:"));

        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> drawingPanel.clear()); // Lambda expression for action

        JButton undo = new JButton("Undo");
        undo.addActionListener(e -> drawingPanel.undo());

        JButton save = new JButton("Save");
        save.addActionListener(e -> drawingPanel.saveImage());

        JButton open = new JButton("Open");
        open.addActionListener(e -> drawingPanel.openImage());

        toolbar.add(clear);
        toolbar.add(undo);
        toolbar.add(save);
        toolbar.add(open);

        
        toolbar.add(new JLabel("      Paint Mode:")); // i add some spaces here to be visually better

        JButton line = new JButton("Line");
        line.addActionListener(e -> drawingPanel.setTool(ToolType.LINE));

        JButton rect = new JButton("Rectangle");
        rect.addActionListener(e -> drawingPanel.setTool(ToolType.RECTANGLE));

        JButton oval = new JButton("Oval");
        oval.addActionListener(e -> drawingPanel.setTool(ToolType.OVAL));

        JButton pencil = new JButton("Pencil");
        pencil.addActionListener(e -> drawingPanel.setTool(ToolType.FREE_HAND));

        JButton eraser = new JButton("Eraser");
        eraser.addActionListener(e -> drawingPanel.setTool(ToolType.ERASER));

        toolbar.add(line);
        toolbar.add(rect);
        toolbar.add(oval);
        toolbar.add(pencil);
        toolbar.add(eraser);

        
        JCheckBox solid = new JCheckBox("Solid", true); // solid is default 
        JCheckBox dotted = new JCheckBox("Dotted");
        JCheckBox filled = new JCheckBox("Filled");

        solid.addActionListener(e -> { 
            dotted.setSelected(false); 		// Uncheck dotted when solid is checked
            drawingPanel.setDotted(false); // Update drawing panel
        });

        dotted.addActionListener(e -> {
            solid.setSelected(false);		// Uncheck solid when dotted is checked
            drawingPanel.setDotted(true);
        });

        filled.addActionListener(e ->
                drawingPanel.setFilled(filled.isSelected()));

        toolbar.add(solid);
        toolbar.add(dotted);
        toolbar.add(filled);

        
        toolbar.add(new JLabel(" Colors:"));

        toolbar.add(createColorButton("Black", Color.BLACK, drawingPanel));
        toolbar.add(createColorButton("Red", Color.RED, drawingPanel));
        toolbar.add(createColorButton("Green", Color.GREEN, drawingPanel));
        toolbar.add(createColorButton("Blue", Color.BLUE, drawingPanel));

        add(toolbar, BorderLayout.NORTH);	// Add toolbar to top of panel
    }

    private JButton createColorButton(String name, Color color, DrawingPanel panel) {
        JButton btn = new JButton(name);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.addActionListener(e -> panel.setColor(color));
        return btn;
    }
}
