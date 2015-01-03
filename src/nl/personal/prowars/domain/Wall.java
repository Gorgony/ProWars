package nl.personal.prowars.domain;

import java.util.ArrayList;

/**
 * Created by maarten on 1/2/2015.
 */
public class Wall extends GameObject {
    public Wall(int x_pos, int y_pos, int dir) {
        super(x_pos, y_pos, dir);
        addSpite(new Sprite("wall_0", 171, 115));
        addSpite(new Sprite("wall_1", 153, 118));
    }

    @Override
    boolean checkCollision(int x, int y) {
        return false;
    }
}