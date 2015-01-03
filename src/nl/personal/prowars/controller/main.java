//TODO: put all the items in a arraylist and add/move a new item with Collections.binairySearch()

package nl.personal.prowars.controller;

import nl.personal.prowars.domain.GameObject;
import nl.personal.prowars.domain.Sprite;
import nl.personal.prowars.domain.Wall;
import org.newdawn.slick.*;

import java.util.ArrayList;

/**
 * Created by Nathan on 29/12/2014.
 */
public class main extends BasicGame{
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 700;
    public static final int TILE_HEIGHT = 256;
    public static final float SCREEN_SCALING = 0.4f;
    public static final int MAX_SCREEN_WIDTH = (int) (SCREEN_WIDTH/SCREEN_SCALING);
    public static final int NR_TILES = MAX_SCREEN_WIDTH/(TILE_HEIGHT*2)  ;

    ArrayList<GameObject> game_objects = new ArrayList<GameObject>();

    Sprite tile, tower, wall;
    Wall test_wall;
    public static void main(String[] args){
        main game = new main("ProWars");
        try{
            AppGameContainer game_container = new AppGameContainer(game);
            game_container.setTargetFrameRate(60);
            game_container.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
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
        //Create game objects and
        //Load sprites


        tile = new Sprite("tile", 256, 128);
        tower = new Sprite("tower", 256, 332);
        wall = new Sprite("wall_0", 171, 115);

        test_wall = new Wall(2, 2, 1);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.scale(SCREEN_SCALING, SCREEN_SCALING);
        int screen_x_offset = (int) (SCREEN_WIDTH/(2*SCREEN_SCALING));
        for(int i = 0; i < NR_TILES; i++){
            for(int j = 0; j <= i; j++){
                int x = (i - j) *TILE_HEIGHT;
                int y = (j)*TILE_HEIGHT;
                int iso_x = x-y;
                int iso_y = ((x+y)/2) ;

                g.drawImage(tile.getImage(), iso_x + screen_x_offset - tile.getX_offset(), iso_y);
            }
        }
        for(int i = 1; i < NR_TILES; i++) {
            for (int j = i; j < NR_TILES; j++) {
                int y = (j)*256;
                int x = (NR_TILES - 1 - (j - i))*256;
                int iso_x = x-y;
                int iso_y = (x+y)/2;
                g.drawImage(tile.getImage(), iso_x + (SCREEN_WIDTH/(2*SCREEN_SCALING)) - tile.getX_offset(), iso_y);
            }
        }
        int x = 0*TILE_HEIGHT;
        int y = 0*TILE_HEIGHT;
        int iso_x = (x-y);
        int iso_y = ((x+y)/2);
        g.drawImage(tower.getImage(), iso_x + screen_x_offset - tower.getX_offset(), iso_y - tower.getY_offset());
        x = 1*TILE_HEIGHT;
        y = 0*TILE_HEIGHT;
        iso_x = (x-y);
        iso_y = ((x+y)/2);
        g.drawImage(wall.getImage(), iso_x + screen_x_offset - wall.getX_offset(), iso_y - wall.getY_offset());
        x = 2*TILE_HEIGHT;
        y = 0*TILE_HEIGHT;
        iso_x = (x-y);
        iso_y = ((x+y)/2);
        g.drawImage(wall.getImage(), iso_x + screen_x_offset - wall.getX_offset(), iso_y - wall.getY_offset());
        x = 3*TILE_HEIGHT;
        y = 0*TILE_HEIGHT;
        iso_x = (x-y);
        iso_y = ((x+y)/2);
        g.drawImage(tower.getImage(), iso_x + screen_x_offset - tower.getX_offset(), iso_y - tower.getY_offset());
        x = 3*TILE_HEIGHT;
        y = 1*TILE_HEIGHT;
        iso_x = (x-y);
        iso_y = ((x+y)/2);
        g.drawImage(wall.getImage(), iso_x + screen_x_offset - wall.getX_offset(), iso_y - wall.getY_offset());
        x = 0*TILE_HEIGHT;
        y = 1*TILE_HEIGHT;
        iso_x = (x-y);
        iso_y = ((x+y)/2);
        g.drawImage(test_wall.getSprite().getImage(), iso_x + screen_x_offset - test_wall.getSprite().getX_offset(), iso_y - test_wall.getSprite().getY_offset());
    }
}
