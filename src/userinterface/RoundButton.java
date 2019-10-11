/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package userinterface;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author kwhiting
 */
public class RoundButton extends RoundedCornerButton
{
    protected RoundButton() 
    {
        super();
    }

    @Override 
    public Dimension getPreferredSize() 
    {
        Dimension d = super.getPreferredSize();
        int s = Math.max(d.width, d.height);
        d.setSize(s, s);
        return d;
    }

    @Override 
    protected void initShape() 
    {
        if (!getBounds().equals(base)) 
        {
            base = getBounds();
            shape = new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
            border = new Ellipse2D.Double(FOCUS_STROKE, FOCUS_STROKE,
                                          getWidth() - 1 - FOCUS_STROKE * 2,
                                          getHeight() - 1 - FOCUS_STROKE * 2);
        }
    }
}