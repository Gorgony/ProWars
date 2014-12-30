package nl.personal.prowars.controller;

import nl.personal.prowars.domain.Sprite;
import org.newdawn.slick.*;

/**
 * Created by Nathan on 29/12/2014.
 */
public class main extends BasicGame{
    Sprite tile, tower, wall;
    public static void main(String[] args){
        main game = new main("ProWars");
        try{
            AppGameContainer game_container = new AppGameContainer(game);
            game_container.setDisplayMode(1920, 1080, false);
            game_container.start();
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

    public main(String title){
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        //Load sprites
        tile = new Sprite("tile");
        tower = new Sprite("tower");
        wall = new Sprite("wall");

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.scale(0.5f, 0.5f);
        int size = 7;
        for(int i = 0; i < size; i++){
            for(int j = 0; j <= i; j++){
                int x = (i - j) *256;
                int y = (j)*256;
                int iso_x = x-y;
                int iso_y = (x+y)/2;
                g.drawImage(tile.getImage(), iso_x + 768 + 896, iso_y + 184);
                if (i == 2 && j == 2){
                    g.drawImage(tower.getImage(), iso_x + 768 + 896, iso_y + 184 - 313);
                }
                if (i == 4 && j == 4){
                    g.drawImage(wall.getImage(), iso_x + 768 + 992, iso_y + 184 - 122);
                }
            }
        }
        for(int i = 1; i < size; i++) {
            for (int j = i; j < size; j++) {
                int y = (j)*256;
                int x = (size - 1 - (j - i))*256;
                int iso_x = x-y;
                int iso_y = (x+y)/2;
                g.drawImage(tile.getImage(), iso_x + 768 + 896, iso_y + 184);
            }
        }
    }
}
