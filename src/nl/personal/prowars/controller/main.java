//TODO: put all the items in a arraylist and add/move a new item with Collections.binairySearch()

package nl.personal.prowars.controller;

import nl.personal.prowars.domain.*;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse; //Why does -> http://slick.ninjacave.com/javadoc/org/newdawn/slick/Input.html#getMouseX() not work
import org.newdawn.slick.*;

import java.security.Key;
import java.util.ArrayList;
import java.awt.Font;

/**
 * Created by Nathan on 29/12/2014, edited by Nathan and Maarten.
 */


public class main extends BasicGame {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 700;
    public static boolean FULL_SCREEN = false;
    public static final int TILE_HEIGHT = 256;
    public static final float SCREEN_SCALING = 1/2f; //TODO: must be mutable
    public static final int NR_TILES =20;
    public static final int MIN_SCREEN_X_OFFSET = -NR_TILES*TILE_HEIGHT + (int) (SCREEN_WIDTH/SCREEN_SCALING);
    public static final int MIN_SCREEN_Y_OFFSET = -NR_TILES*TILE_HEIGHT + (int) (SCREEN_HEIGHT/SCREEN_SCALING);
    public static final int MAX_SCREEN_X_OFFSET = NR_TILES*TILE_HEIGHT;
    public static final int MAX_SCREEN_Y_OFFSET = TILE_HEIGHT;
//    public int screen_y_offset = TILE_HEIGHT;
//    public int screen_x_offset = (int) (SCREEN_WIDTH/(2*SCREEN_SCALING));
    public int screen_y_offset = 0;
    public int screen_x_offset = 0;
    int mouse_x;
    int mouse_y;
    Unit unit1;
    Unit unit2;
    Font font = new Font("Verdana", Font.BOLD, 32);
    TrueTypeFont ttf;
    Color text_background_color;
    ConsoleText ct;
    MouseObject mo;

    ArrayList<GameObject> game_objects = new ArrayList<GameObject>();

    Sprite tile;
    public static void main(String[] args){
        main game = new main("ProWars");
        try{
            AppGameContainer game_container = new AppGameContainer(game);
            game_container.setTargetFrameRate(60);
            game_container.setVSync(true);
            game_container.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, FULL_SCREEN);
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
        text_background_color = new Color(64, 64, 64, 192);
        ttf = new TrueTypeFont(font, true);
        tile = new Sprite("tile", 256, 128);
        ct = new ConsoleText();
//        mo = new CommandAddWall(Mouse.getX() + screen_x_offset, Mouse.getY() + screen_y_offset);
        addWall(2,2);
        addWall(2,3);
        addWall(2,4);
    }

    @Override
    public  void keyReleased(int key, char c){
        if (c == 13){
            System.out.println(mouse_x);
            System.out.println(mouse_y);
            System.out.println(screen_x_offset);
            System.out.println(screen_y_offset);
        }
//        if (c == 13){ //Show console/execute command
//            if (ct.isActive()){ //Execute command
//                String command = ct.getText().toLowerCase();
//                if (command.equals("")){
//                    ct.setActive(false);
//                } else if (command.equals("help")){ //TODO: Let the command be handled by a separate class
//                    ct.setText("Shows this help");
//                } else if (command.equals("add wall")){
//                    mo = new CommandAddWall(Mouse.getX() + screen_x_offset, Mouse.getY() + screen_y_offset);
//                }
//            } else{ //Show console
//                ct.setActive(true);
//            }
//        }
//        if (ct.isActive()){
//            if (c >= 32 && c <= 126){
//                ct.addChar(c);
//            } else if (c == 8){
//                ct.removeChar();
//            }
//        }
    }


    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //Get input data
        mouse_x = Mouse.getX();
        mouse_y = Mouse.getY();

        if (mouse_x < 25){
            screen_x_offset += 16;
            if (screen_x_offset > MAX_SCREEN_X_OFFSET){
                screen_x_offset = MAX_SCREEN_X_OFFSET;
            }
        }
        else if (mouse_x > (SCREEN_WIDTH - 25)){
            screen_x_offset -= 16;
            if (screen_x_offset < MIN_SCREEN_X_OFFSET){
                screen_x_offset = MIN_SCREEN_X_OFFSET;
            }
        }
        if (mouse_y < 25){
            screen_y_offset -= 16;
            if (screen_y_offset < MIN_SCREEN_Y_OFFSET){
                screen_y_offset = MIN_SCREEN_Y_OFFSET;
            }
        }
        else if (mouse_y > (SCREEN_HEIGHT - 25)){
            screen_y_offset += 16;
            if (screen_y_offset > MAX_SCREEN_Y_OFFSET){
                screen_y_offset = MAX_SCREEN_Y_OFFSET;
            }
        }
        if (mo != null){
            mo.setMousePos(screen_x_offset + mouse_x, screen_y_offset - mouse_y);
        }

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.scale(SCREEN_SCALING, SCREEN_SCALING);
        renderNormal(container, g);
    }

    public GameObject searchBuilding(int x, int y){
        GameObject result = null;
        for(GameObject g : game_objects){
            if(g.getX_pos() == x && g.getY_pos() == y && g.getType().equals("Building")){
                result = g;
            }
        }
        return result;
    }

    public void addWall(int x, int y){
        //Wall tempWall = new Wall(x*TILE_HEIGHT,y*TILE_HEIGHT); //TODO: eigenlijk is dit correct.. moet elke tile een eigen List van GameObejcts krijgen??
        Wall tempWall = new Wall(x,y);
        game_objects.add(tempWall);
        tempWall.setDirection(game_objects,true);
    }

    public void renderNormal(GameContainer container, Graphics g) throws SlickException {
        //Render tiles, at each tile check if a building must be rendered on top of it.
        for(int i = 0; i < NR_TILES; i++){
            for(int j = 0; j <= i; j++){
                int x_tile = (i - j);
                int y_tile = j;
                int x = x_tile * TILE_HEIGHT;
                int y = y_tile * TILE_HEIGHT;
                int iso_x = x-y;
                iso_x += screen_x_offset;
                int iso_y = ((x+y)/2);
                iso_y += screen_y_offset;
                g.drawImage(tile.getImage(), iso_x - tile.getX_offset(), iso_y - tile.getY_offset());
                GameObject temp = searchBuilding(x_tile,y_tile);
                if(temp != null){
                    //g.drawImage(temp.getSprite().getImage(), temp.getIsoX() + screen_x_offset, temp.getIsoY() + screen_y_offset); dit zou eigenlijk ook moeten, maar dan moet er eerst worden nagedacht over andere zaken. Bovendien zal niet elk object altijd zichtbaar zijn en daarbij ook niet te worden gedrawed..
                    g.drawImage(temp.getSprite().getImage(), iso_x - temp.getSprite().getX_offset(), iso_y - temp.getSprite().getY_offset());
                }
            }
        }
        for(int i = 1; i < NR_TILES; i++) {
            for (int j = i; j < NR_TILES; j++) {
                int x_tile = (NR_TILES - 1 - (j - i));
                int y_tile = j;
                int x = x_tile*TILE_HEIGHT;
                int y = y_tile*TILE_HEIGHT;
                int iso_x = x-y;
                iso_x += screen_x_offset;
                int iso_y = (x+y)/2;
                iso_y += screen_y_offset;
                g.drawImage(tile.getImage(), iso_x - tile.getX_offset(), iso_y);
                GameObject temp = searchBuilding(x_tile,y_tile);
                if(temp != null) {
                    g.drawImage(temp.getSprite().getImage(), iso_x - temp.getSprite().getX_offset(), iso_y - temp.getSprite().getY_offset());
                }
            }
        }
        if (mo != null){
            mo.draw(g, screen_x_offset, screen_y_offset);
        }

        //Draw console text
        if (ct.isActive()){
            g.setColor(text_background_color);
            g.fillRect(0, (SCREEN_HEIGHT / SCREEN_SCALING) - 39, (SCREEN_WIDTH / SCREEN_SCALING), 39);
            g.setColor(Color.black);
            g.setFont(ttf);
            g.drawString(ct.getText() + "_", 3, (SCREEN_HEIGHT / SCREEN_SCALING) - 40);
        }

    }

    public void renderSorted(GameContainer container, Graphics g) throws SlickException{
        int screen_x_offset = (int) (SCREEN_WIDTH/(2*SCREEN_SCALING));
        //Render tiles
        for(int i = 0; i < NR_TILES; i++){
            for(int j = 0; j <= i; j++){
                int x_tile = (i - j);
                int y_tile = j;
                int x = x_tile * TILE_HEIGHT;
                int y = y_tile * TILE_HEIGHT;
                int iso_x = x-y;
                int iso_y = ((x+y)/2) ;
                g.drawImage(tile.getImage(), iso_x + screen_x_offset - tile.getX_offset(), iso_y);
            }
        }
        for(int i = 1; i < NR_TILES; i++) {
            for (int j = i; j < NR_TILES; j++) {
                int x_tile = (NR_TILES - 1 - (j - i));
                int y_tile = j;
                int x = x_tile*TILE_HEIGHT;
                int y = y_tile*TILE_HEIGHT;
                int iso_x = x-y;
                int iso_y = (x+y)/2;
                g.drawImage(tile.getImage(), iso_x + screen_x_offset - tile.getX_offset(), iso_y);
            }
        }
    }
    //Render all the items in the game_objects list
}
