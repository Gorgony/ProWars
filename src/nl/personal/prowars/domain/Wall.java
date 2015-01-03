package nl.personal.prowars.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by maarten on 1/2/2015.
 */
public class Wall extends GameObject {
    public Wall(int x_pos, int y_pos, int dir) {
        super(x_pos, y_pos, dir);
        addSprite(new Sprite("tower", 256, 332));
        addSprite(new Sprite("wall_0", 171, 115));
        addSprite(new Sprite("wall_1", 153, 118));
    }

    @Override
    boolean checkCollision(int x, int y) {
        return false;
    }

    @Override
    public Sprite getSprite(){
        if(dir < 3) {
            return spriteList.get(dir);
        } else {
            return spriteList.get(0);
        }
    }

    public void setDirection(ArrayList<GameObject> objects){
        boolean top, left, right, bottom;
        for(GameObject g : objects){
            if(g.getType().equals("Wall")) {
                if (g.getX_pos() == getX_pos() && g.getY_pos() == (getY_pos() - 1)) {
                    top = true;
                } else if (g.getX_pos() == (getX_pos() + 1) && g.getY_pos() == getY_pos()) {
                    right = true;
                } else if (g.getX_pos() == (getX_pos() - 1) && g.getY_pos() == getY_pos()) {
                    left = true;
                } else if (g.getX_pos() == getX_pos() && g.getY_pos() == (getY_pos() + 1)) {
                    bottom = true;
                }
            }
        }
        if()
    }
}