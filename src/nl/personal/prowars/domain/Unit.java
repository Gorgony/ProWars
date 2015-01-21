package nl.personal.prowars.domain;

/**
 * Created by Nathan on 05/01/2015.
 */
public class Unit extends GameObject {
    public Unit(int x, int y){
        super(x,y,"Unit");
        addSprite(new Sprite("guy_0", -221,98));
        addSprite(new Sprite("guy_1", -221,98));
        addSprite(new Sprite("guy_2", -221,98));
        addSprite(new Sprite("guy_3", -221,98));
        addSprite(new Sprite("guy_4", -221,98));
        addSprite(new Sprite("guy_5", -221,98));
        addSprite(new Sprite("guy_6", -221,98));
        addSprite(new Sprite("guy_7", -221,98));
    }

    @Override
    boolean checkCollision(int x, int y) {
        return false;
    }
}
