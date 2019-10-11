/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package userinterface;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author kwhiting
 */
public class RoundedCornerButton extends JButton
{
    private static final double ARC_WIDTH  = 16d;
    private static final double ARC_HEIGHT = 16d;
    protected static final int FOCUS_STROKE = 2;
    protected final Color fc = new Color(100, 150, 255, 200);
    protected final Color ac = new Color(230, 230, 230);
    protected final Color rc = Color.ORANGE;
    protected Shape shape;
    protected Shape border;
    protected Shape base;

    protected RoundedCornerButton() 
    {
        super();
    }

    @Override 
    public void updateUI() 
    {
        super.updateUI();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(250, 250, 250));
        initShape();
    }

    protected void initShape() 
    {
        if (!getBounds().equals(base)) 
        {
            base = getBounds();
            shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
            border = new RoundRectangle2D.Double(FOCUS_STROKE, FOCUS_STROKE,
                                                 getWidth() - 1 - FOCUS_STROKE * 2,
                                                 getHeight() - 1 - FOCUS_STROKE * 2,
                                                 ARC_WIDTH, ARC_HEIGHT);
        }
    }

    private void paintFocusAndRollover(Graphics2D g2, Color color) 
    {
        g2.setPaint(new GradientPaint(0, 0, color, getWidth() - 1, getHeight() - 1, color.brighter(), true));
        g2.fill(shape);
        g2.setPaint(getBackground());
        g2.fill(border);
    }

    @Override 
    protected void paintComponent(Graphics g) 
    {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) 
        {
            g2.setPaint(ac);
            g2.fill(shape);
        } 
        else if (isRolloverEnabled() && getModel().isRollover()) 
        {
            paintFocusAndRollover(g2, rc);
        } 
        else if (hasFocus()) 
        {
            paintFocusAndRollover(g2, fc);
        } 
        else 
        {
            g2.setPaint(getBackground());
            g2.fill(shape);
        }

        g2.dispose();
//        super.paintComponent(g);
    }

    @Override 
    protected void paintBorder(Graphics g) 
    {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getForeground());
        g2.draw(shape);
        g2.dispose();
    }

    @Override 
    public boolean contains(int x, int y) 
    {
        initShape();
        return shape != null && shape.contains(x, y);
    }    
}
