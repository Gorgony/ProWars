package nl.personal.prowars.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by maarten on 12/30/2014.
 */
public class Sprite {
    Image[] images;

    public Sprite(String name, int nr_images){
        images = new Image[nr_images];
        for(int i = 0; i < nr_images; i++){
            images[i] = loadImage(name + "_" + i);
        }
    }

    public Sprite(String name){
        images = new Image[1];
        images[0] = loadImage(name);
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
        return images[0];
    }

    public Image getImage(int index){
        try {
            return images[index];
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

}
