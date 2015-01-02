package nl.personal.prowars.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by maarten on 12/30/2014.
 */
public class Sprite {
    private Image image;
    private int x_offset, y_offset;

    public Sprite(String name, int x_offset, int y_offset){
        image = loadImage(name);
        this.x_offset = x_offset;
        this.y_offset = y_offset;
    }

    private Image loadImage(String name){
        try{
            Image img = new Image("res/" + name + ".bmp");
            return img;
        } catch (SlickException e){
            e.printStackTrace();
        }
        return null;
    }

    public Image getImage(){
        return image;
    }

    public int getX_offset() {
        return x_offset;
    }

    public int getY_offset() {
        return y_offset;
    }
}
