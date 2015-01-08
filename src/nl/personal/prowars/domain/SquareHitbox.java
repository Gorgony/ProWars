package nl.personal.prowars.domain;

/**
 * Created by maarten on 1/8/2015.
 */
public class SquareHitbox  extends Hitbox{
    Rect box;

    public SquareHitbox(Rect box) {
        this.box = box;
    }

    @Override
    boolean checkCollision(int x, int y) {
        return (x >= box.x_top && x <= box.x_bottom && y >= box.y_top && y <= box.y_bottom);
    }
}
