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
        sprite = new Sprite("wall_0", -119, 165);
        setMousePos(x_pos, y_pos);
    }

    @Override
    public boolean onClick() {
        return true;
    }

    @Override
    public void setMousePos(int x, int y) {
        x_pos = ((2*y+x)/2)-128;
        x_pos -= x_pos%main.TILE_HEIGHT;
        if (x_pos < 0){
            x_pos = 0;
        }
        if (x_pos >= main.NR_TILES*main.TILE_HEIGHT){
            x_pos = (main.NR_TILES - 1)*main.TILE_HEIGHT;
        }
        y_pos = ((2*y-x)/2) + 128;
        y_pos -= y_pos%main.TILE_HEIGHT;
        if (y_pos < 0){
            y_pos = 0;
        }
        if (y_pos >= main.NR_TILES*main.TILE_HEIGHT){
            y_pos = (main.NR_TILES - 1)*main.TILE_HEIGHT;
        }
    }

    @Override
    public void draw(Graphics g, int x_offset, int y_offset) {
        g.drawImage(sprite.getImage(), getIsoX() - sprite.getX_offset() + x_offset, getIsoY() - sprite.getY_offset() + y_offset);
    }

    @Override
    public int getX() {
        return x_pos;
    }

    @Override
    public int getY() {
        return y_pos;
    }

    public int getIsoX(){
        return (x_pos-y_pos);
    }

    public int getIsoY(){
        return ((x_pos+y_pos)/2);
    }


}
