package nl.personal.prowars.domain;

/**
 * Created by Nathan on 05/01/2015.
 */
public class Unit extends GameObject {
    public Unit(int x, int y){
        super(x,y,"Unit");
        addSprite(new Sprite("guy_0",35,130));
        addSprite(new Sprite("guy_1",35,130));
        addSprite(new Sprite("guy_2",35,130));
        addSprite(new Sprite("guy_3",35,130));
        addSprite(new Sprite("guy_4",35,130));
        addSprite(new Sprite("guy_5",35,130));
        addSprite(new Sprite("guy_6",35,130));
        addSprite(new Sprite("guy_7",35,130));
    }

    @Override
    boolean checkCollision(int x, int y) {
        return false;
    }
}
