package nl.personal.prowars.domain.commands;

import nl.personal.prowars.controller.main;
import nl.personal.prowars.domain.GameObject;
import nl.personal.prowars.domain.Sprite;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by maarten on 1/21/2015.
 */
public class CommandAddUnit  extends MouseObject {
    private int x_pos, y_pos;
    private Sprite sprite;

    public CommandAddUnit(int x_pos, int y_pos) {
        super("unit");
        sprite = new Sprite("guy_4", -221,98);
        setMousePos(x_pos, y_pos);
    }

    @Override
    public boolean onClick(ArrayList<GameObject> game_objects) {
        return true;//TODO:collision check
    }

    @Override
    public void setMousePos(int x, int y) {
        x_pos = ((2*y+x)/2)-128;
        x_pos -= x_pos%96 ;
        if (x_pos < 0){
            x_pos = 0;
        }
        if (x_pos >= main.NR_TILES*main.TILE_HEIGHT){
            x_pos = (main.NR_TILES - 1)*main.TILE_HEIGHT;
        }
        y_pos = ((2*y-x)/2) + 128;
        y_pos -= y_pos%96;
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
