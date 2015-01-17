package nl.personal.prowars.domain;

import nl.personal.prowars.controller.main;
import org.newdawn.slick.Graphics;

/**
 * Created by maarten on 15-1-2015.
 */
public class CommandAddWall extends MouseObject {
    private int x_pos, y_pos;
    private Sprite sprite;

    public CommandAddWall(int x_pos, int y_pos) {
        sprite = new Sprite("wall_0",  137, 165);
        setMousePos(x_pos, y_pos);
    }

    @Override
    public boolean onClick() {
        return false;
    }

    @Override
    public void setMousePos(int x, int y) {
        x_pos = 0;//??
        y_pos = 0;//??
    }

    @Override
    public void draw(Graphics g, int x_offset, int y_offset) {
        g.drawImage(sprite.getImage(), getIsoX() - sprite.getX_offset() + x_offset, getIsoY() - sprite.getY_offset() + y_offset);
    }

    public int getIsoX(){
        return (x_pos-y_pos);
    }

    public int getIsoY(){
        return ((x_pos+y_pos)/2);
    }
}
