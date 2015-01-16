package nl.personal.prowars.domain;

import org.newdawn.slick.Graphics;

/**
 * Created by maarten on 14-1-2015.
 */
public abstract class MouseObject {
    public abstract boolean onClick();
    public abstract void setMousePos(int x, int y);
    public abstract void draw(Graphics g, int x_offset, int y_offset);
}
