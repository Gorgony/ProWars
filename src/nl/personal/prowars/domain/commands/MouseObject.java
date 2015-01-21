package nl.personal.prowars.domain.commands;

import nl.personal.prowars.domain.GameObject;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by maarten on 14-1-2015.
 */
public abstract class MouseObject {
    private String type;
    public MouseObject(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public abstract boolean onClick(ArrayList<GameObject> game_objects);
    public abstract void setMousePos(int x, int y);
    public abstract void draw(Graphics g, int x_offset, int y_offset);
    public abstract int getX();
    public abstract int getY();
}
