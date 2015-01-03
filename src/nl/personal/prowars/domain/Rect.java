package nl.personal.prowars.domain;

/**
 * Created by maarten on 1/2/2015.
 */
public class Rect extends Hitbox {
    int x_top, y_top, x_bottom, y_bottom;

    public Rect(int x_size, int y_size) {
        this(0, 0, x_size, y_size);
    }

    public Rect(int x_top, int y_top, int x_bottom, int y_bottom) {
        this.x_top = x_top;
        this.y_top = y_top;
        this.x_bottom = x_bottom;
        this.y_bottom = y_bottom;
    }

    @Override
    boolean checkCollision(int x, int y) {
        if (x >= x_top && x <= x_bottom && y >= y_top && y <= y_bottom){
            return true;
        }
        return false;
    }
}
