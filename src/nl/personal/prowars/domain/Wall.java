package nl.personal.prowars.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by maarten on 1/2/2015.
 */
public class Wall extends GameObject {
    public Wall(int x_pos, int y_pos) {
        super(x_pos, y_pos, "Building");
        addSprite(new Sprite("tower", 256, 332));
        Sprite temp;
        temp = new Sprite("wall_0", 171, 115);
        temp.addHitbox(new SquareHitbox(new Rect(8, 64))); //Just for testing, wouldn't know if the numbers are right..
        addSprite(temp);
        addSprite(new Sprite("wall_1", 153, 118));
    }

    @Override
    boolean checkCollision(int x, int y) {
        return false;
    }

    @Override
    public Sprite getSprite(){
        if(getDir() == 10) {
            return getSpriteList().get(1);
        } else if(getDir() == 5){
            return getSpriteList().get(2);
        } else {
            return getSpriteList().get(0);
        }
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