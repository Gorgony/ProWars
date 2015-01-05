package nl.personal.prowars.domain;


import java.util.ArrayList;

/**
 * Created by maarten on 12/29/2014.
 */

public abstract class GameObject {
    private int x_pos, y_pos, dir;
    private String type;
    private ArrayList<Sprite> spriteList; //Deze moet static zijn, maar dan wel bij de klassen die GameObject extenden en niet bij GameObject zelf

    public GameObject(int x_pos, int y_pos, String type){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.type = type;
        this.dir = 0;
        this.spriteList = new ArrayList<Sprite>();
    }

    public GameObject(int x_pos, int y_pos, String type, int dir) {
        this(x_pos,y_pos,type);
        this.dir = dir;
    }

    public GameObject(int x_pos, int y_pos,String type, int dir, ArrayList<Sprite> spriteList) {
        this(x_pos, y_pos, type, dir);
        this.spriteList = spriteList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
//        if ( (dir >= spriteList.size()) ||  (dir < 0) ){
//            throw new IndexOutOfBoundsException();
//        }
        this.dir = dir;
    }

    public ArrayList<Sprite> getSpriteList(){
        return spriteList;
    }

    public void addSprite(Sprite sprite){
        spriteList.add(sprite);
    }

    public Sprite getSprite(){
        return spriteList.get(dir);
    }

    abstract boolean checkCollision(int x, int y);

    public int getIsoX(){
        return (x_pos-y_pos);
    }

    public int getIsoY(){
        return (x_pos+y_pos)/2;
    }
}