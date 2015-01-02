package nl.personal.prowars.domain;


import java.util.ArrayList;

/**
 * Created by maarten on 12/29/2014.
 */

public abstract class GameObject {
    private int x_pos, y_pos, dir;
    private ArrayList<Sprite> spriteList;

    public GameObject(int x_pos, int y_pos, int dir) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.dir = dir;
        this.spriteList = new ArrayList<Sprite>(4);
    }

    public GameObject(int x_pos, int y_pos, int dir, ArrayList<Sprite> spriteList) {
        this(x_pos, y_pos, dir);
        this.spriteList = spriteList;
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

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        if ( (dir >= spriteList.size()) ||  (dir < 0) ){
            throw new IndexOutOfBoundsException();
        }
        this.dir = dir;
    }

    public void addSpite(Sprite sprite){
        spriteList.add(sprite);
    }

    public Sprite getSprite(){
        return spriteList.get(dir);
    }
}