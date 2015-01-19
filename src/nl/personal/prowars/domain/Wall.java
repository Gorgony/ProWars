package nl.personal.prowars.domain;

import java.util.ArrayList;

/**
 * Created by maarten on 1/2/2015.
 */
public class Wall extends GameObject {
    public Wall(int x_pos, int y_pos) {
        super(x_pos, y_pos, "Building");
        addSprite(new Sprite("wall_0", -119, 165));
        addSprite(new Sprite("wall_1", -119, 165));
        addSprite(new Sprite("wall_2", -119, 165));
        addSprite(new Sprite("wall_3", -119, 165));
        addSprite(new Sprite("wall_4", -94, 165));
        addSprite(new Sprite("wall_5", -94, 121));
        addSprite(new Sprite("wall_6", -94, 165));
        addSprite(new Sprite("wall_7", -94, 165));
        addSprite(new Sprite("wall_8", -94, 165));
        addSprite(new Sprite("wall_9", -94, 165));
        addSprite(new Sprite("wall_10", -94, 121));
        addSprite(new Sprite("wall_11", -94, 165));
        addSprite(new Sprite("wall_12", -94, 165));
        addSprite(new Sprite("wall_13", -94, 165));
        addSprite(new Sprite("wall_14", -94, 165));
        addSprite(new Sprite("wall_15", -94, 165));
    }

    @Override
    boolean checkCollision(int x, int y) {
        return false;
    }

    @Override
    public Sprite getSprite(){
        return getSpriteList().get(getDir());
    }

    public void setDirection(ArrayList<GameObject> objects, boolean update_others) {
        int temp_dir = 0;
        for (GameObject g : objects) {
            if (g instanceof Wall) {
                if (g.getX_pos() == getX_pos() && g.getY_pos() == (getY_pos() - 1)) {
                    temp_dir += 1; //Bovenkant is verbonden met een muur
                    if(update_others) {((Wall) g).setDirection(objects,false);}
                } else if (g.getX_pos() == (getX_pos() + 1) && g.getY_pos() == getY_pos()) {
                    temp_dir += 2; //Rechterkant is verbonden met een muur
                    if(update_others) {((Wall) g).setDirection(objects,false);}
                } else if (g.getX_pos() == (getX_pos() - 1) && g.getY_pos() == getY_pos()) {
                    temp_dir += 8; //Onderkant is verbonden met een muur
                    if(update_others) {((Wall) g).setDirection(objects,false);}
                } else if (g.getX_pos() == getX_pos() && g.getY_pos() == (getY_pos() + 1)) {
                    temp_dir += 4; //Linkerkant is verbonden met een muur
                    if(update_others) {((Wall) g).setDirection(objects,false);}
                }
            }
        }
        this.setDir(temp_dir);
    }
}