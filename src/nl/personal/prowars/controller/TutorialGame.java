package nl.personal.prowars.controller;

/**
 * Created by maarten on 12/29/2014.
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TutorialGame extends BasicGame {
    Image left;
    Image circle;

    public TutorialGame(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        arg1.drawString("Hello world!", 200, 200);
        arg1.setColor(Color.red);

        arg1.setColor(Color.blue);
        arg1.drawRect(180,  170, 150,  80);

        arg1.setAntiAlias(true);
        arg1.setColor(Color.red);
        arg1.drawLine(250, 190, 300, 230);
        arg1.drawLine(250, 230, 300, 190);

        arg1.setColor(new Color(0f,0.6f,0.8f,0.5f)); // transparent bluish, 50% transparent
        arg1.fillOval(190, 180, 60, 60);

        left.setRotation(25);
        arg1.drawImage(left, 150, 100);
        arg1.drawImage(left,  182,  100,  Color.green);
        arg1.drawImage(circle, 214,  100);
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        left = new Image("images/Left.png");
        circle = new Image("images/Block.png");

    }

    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        TutorialGame game = new TutorialGame("Slick tut");
        try{
            AppGameContainer container = new AppGameContainer(game);
            container.start();
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

}
