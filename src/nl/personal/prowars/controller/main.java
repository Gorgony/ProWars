//TODO: put all the items in a arraylist and add/move a new item with Collections.binairySearch()

package nl.personal.prowars.controller;

import nl.personal.prowars.domain.*;
import nl.personal.prowars.domain.commands.CommandAddUnit;
import nl.personal.prowars.domain.commands.CommandAddWall;
import nl.personal.prowars.domain.commands.ConsoleText;
import nl.personal.prowars.domain.commands.MouseObject;
import org.lwjgl.input.Mouse; //Why does -> http://slick.ninjacave.com/javadoc/org/newdawn/slick/Input.html#getMouseX() not work
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Nathan on 29/12/2014, edited by Nathan and Maarten.
 */


public class main extends BasicGame {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static boolean FULL_SCREEN = true;
    public static final int TILE_HEIGHT = 256;
    public static final float SCREEN_SCALING = 1/2f; //TODO: must be mutable
    public static final int NR_TILES =20;
    public static int MIN_SCREEN_X_OFFSET;
    public static int MIN_SCREEN_Y_OFFSET;
    public static int MAX_SCREEN_X_OFFSET;
    public static int MAX_SCREEN_Y_OFFSET;
    public static int screen_y_offset;
    public static int screen_x_offset;
    int mouse_x;
    int mouse_y;
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
            SCREEN_WIDTH = game_container.getScreenWidth();
            SCREEN_HEIGHT = game_container.getScreenHeight();
            MIN_SCREEN_X_OFFSET = -(NR_TILES + 1)*TILE_HEIGHT + (int) (SCREEN_WIDTH/SCREEN_SCALING);
            MIN_SCREEN_Y_OFFSET = -NR_TILES*TILE_HEIGHT + (int) (SCREEN_HEIGHT/SCREEN_SCALING);
            MAX_SCREEN_X_OFFSET = NR_TILES*TILE_HEIGHT;
            MAX_SCREEN_Y_OFFSET = TILE_HEIGHT;
            screen_y_offset = TILE_HEIGHT;
            screen_x_offset = (int) (SCREEN_WIDTH/(2*SCREEN_SCALING) - TILE_HEIGHT);

            System.out.println(game_container.getScreenWidth());
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
    }

    @Override
    public  void keyReleased(int key, char c){
        if (c == 13){ //Show console/execute command
            if (ct.isActive()){ //Execute command
                String command = ct.getText().toLowerCase();
                if (command.equals("")){
                    ct.setActive(false);
                } else if (command.equals("help")){ //TODO: Let the command be handled by a separate class
                    ct.setText("Shows this help");
                } else if (command.equals("add wall")){
                    mo = new CommandAddWall(Mouse.getX() + screen_x_offset, Mouse.getY() + screen_y_offset);
                } else if (command.equals("add unit")){
                    mo = new CommandAddUnit(Mouse.getX() + screen_x_offset, Mouse.getY() + screen_y_offset);
                }
            } else{ //Show console
                ct.setActive(true);
            }
        }
        if (ct.isActive()){
            if (c >= 32 && c <= 126){
                ct.addChar(c);
            } else if (c == 8){
                ct.removeChar();
            }
        }
        if (key == 1){
            ct.setActive(false);
            mo = null;
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount){
        if (button == 0){
            if (mo != null){
                if (mo.onClick(game_objects)){
                    if (mo.getType().equals("wall")){
                        addWall(mo.getX()/TILE_HEIGHT, mo.getY()/TILE_HEIGHT);
                        Collections.sort(game_objects);
                    } else if (mo.getType().equals("unit")){
                        Unit temp = new Unit(mo.getX(), mo.getY());
                        temp.setDir(4);
                        game_objects.add(temp);
                        Collections.sort(game_objects);
                    }
                    ct.setActive(false);
                }
            }
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //Get input data
        mouse_x = Mouse.getX();
        mouse_y = Mouse.getY();

        if (mouse_x < 25){
            screen_x_offset += 24;
            if (screen_x_offset > MAX_SCREEN_X_OFFSET){
                screen_x_offset = MAX_SCREEN_X_OFFSET;
            }
        }
        else if (mouse_x > (SCREEN_WIDTH - 25)){
            screen_x_offset -= 24;
            if (screen_x_offset < MIN_SCREEN_X_OFFSET){
                screen_x_offset = MIN_SCREEN_X_OFFSET;
            }
        }
        if (mouse_y < 25){
            screen_y_offset -= 24;
            if (screen_y_offset < MIN_SCREEN_Y_OFFSET){
                screen_y_offset = MIN_SCREEN_Y_OFFSET;
            }
        }
        else if (mouse_y > (SCREEN_HEIGHT - 25)){
            screen_y_offset += 24;
            if (screen_y_offset > MAX_SCREEN_Y_OFFSET){
                screen_y_offset = MAX_SCREEN_Y_OFFSET;
            }
        }
        if (mo != null){
            mo.setMousePos((int) (mouse_x/SCREEN_SCALING)-screen_x_offset, (int) ((SCREEN_HEIGHT-mouse_y)/SCREEN_SCALING) - screen_y_offset);
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.scale(SCREEN_SCALING, SCREEN_SCALING);
        renderNormal(container, g);
    }

    public void addWall(int x, int y){
        Wall tempWall = new Wall(x*TILE_HEIGHT,y*TILE_HEIGHT);
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
                g.drawImage(tile.getImage(), iso_x , iso_y);
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
                g.drawImage(tile.getImage(), iso_x , iso_y);
            }
        }
        for(GameObject game_object: game_objects){
            g.drawImage(game_object.getSprite().getImage(), game_object.getIsoX() - game_object.getSprite().getX_offset() + screen_x_offset, game_object.getIsoY() - game_object.getSprite().getY_offset() + screen_y_offset);
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
}
