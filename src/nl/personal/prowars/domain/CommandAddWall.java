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
        sprite = new Sprite("wall_15", 160, 165);
        setMousePos(x_pos, y_pos);
    }

    @Override
    boolean onClick() {
        return false;
    }

    @Override
    void setMousePos(int x, int y) {
        x_pos = x - (x % main.TILE_HEIGHT);
        y_pos = y - (y % main.TILE_HEIGHT);
    }

    @Override
    void draw(Graphics g, int x_offset, int y_offset) {

    }
}
