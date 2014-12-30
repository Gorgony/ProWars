package nl.personal.prowars.domain;


/**
 * Created by maarten on 12/29/2014.
 */

public abstract class GameObject {
    private int x_pos, y_pos, dir;
    Sprite sprite;

    public GameObject(int x_pos, int y_pos, int dir, Sprite sprite) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.dir = dir;
        this.sprite = sprite;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }
}