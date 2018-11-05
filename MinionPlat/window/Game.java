package window;


/**
 * Write a description of class Game here.
 * 
 * @author Alex Thomas
 * @version January 22, 2017
 */

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.Random;
import objects.Block;
import framework.ObjectId;
import objects.Player;
import framework.KeyInput;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import framework.Texture;
import objects.Lava;
import framework.STATE;
import framework.MouseInput;
import framework.SplashScreenDriver;
import objects.Flag;
import objects.MovingBlock;
import objects.Coin;
import objects.StillEnemy;
import objects.MovingEnemy;
import java.awt.Font;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = -6261436164361361187L;
    
    public static boolean levelLoaded = false;
    private boolean running = false;
    private Thread thread;
      
    public static int WIDTH, HEIGHT;       
    
    public static int fps = 0;
    public static int ticks = 0;  
    
    public static int timeMinutes = 0;
    public static int timeSeconds = 0;   
    public static int coinCounter = 0;   
    
    public static final int MAX_LEVEL = 4;
    public static int currentLevel = 1;
    public static int score = 0;
    public static int scoreAddedThisLevel = 0;
    
    private BufferedImage level = null;
    
    public STATE State;
    
    public static STATE state = STATE.MENU;    
    
    private Font font = new Font("arial", Font.BOLD, 30);
    
    //Object
    public static Handler handler = new Handler();
    public static Camera cam;
    public static Texture tex = new Texture(); 
    private Menu menu;
    private Game game;
    private Help help;
    private Controls controls;
    
    public void run()
    {
        init();   
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                render();
                frames++;
                updates++;
                delta--;
            }
            //render();
            //frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                if(state == STATE.GAME)
                    timeSeconds++;
                fps = frames;
                ticks = updates;
                System.out.println("Frames: " + fps + ", Updates: " + ticks);
                //System.out.println("Rendered " + Handler.objectsRendered + " objects!");
                Handler.objectsRendered = 0;
                //System.out.println("The Camera is capturing (" + -cam.getX() + "," + cam.getY() + ")");
                //System.out.println("The Camera is capturing (" + cam.getX() + "," + cam.getY() + ")");
                frames = 0;
                updates = 0;
            }
            if(timeSeconds >= 60 && state == STATE.GAME)
            {
                timeMinutes++;
                timeSeconds = 0;
            }
        }
    }    
    
    private void init()
    {
        WIDTH = getWidth();
        HEIGHT = getHeight();        
        
        menu = new Menu();
        help = new Help();
        controls = new Controls();
        cam = new Camera(0,0);              
                                
        //handler.addObject(new Player(100,100,handler, ObjectId.Player));
        
        //handler.createLevel();
        
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(menu, help, controls));
    }
    
    private void tick()
    {       
        if(state == STATE.GAME)
        {                
            handler.tick();
            for(int i = 0; i < handler.object.size(); i++)
            {
                if(handler.object.get(i).getId() == ObjectId.Player)
                {
                    cam.tick(handler.object.get(i));
                }
            }       
        }
        else if(state == STATE.MENU)
        {
            if(!levelLoaded)
            {
                BufferedImageLoader loader = new BufferedImageLoader();        
                level = loader.loadImage("/res/level" + currentLevel +".png"); // loading the level
                loadImageLevel(level);
                levelLoaded = true;
            }
        }
        else if(state == STATE.HELP)
        {
            if(!levelLoaded)
            {
                BufferedImageLoader loader = new BufferedImageLoader();        
                level = loader.loadImage("/res/level" + currentLevel +".png"); // loading the level
                loadImageLevel(level);
                levelLoaded = true;
            }
        }
        else if(state == STATE.CONTROLS)
        {
            if(!levelLoaded)
            {
                BufferedImageLoader loader = new BufferedImageLoader();        
                level = loader.loadImage("/res/level" + currentLevel +".png"); // loading the level
                loadImageLevel(level);
                levelLoaded = true;
            }
        }
        else if(state == STATE.LOADING)
        {
            if(!levelLoaded)
            {
                BufferedImageLoader loader = new BufferedImageLoader();        
                level = loader.loadImage("/res/level" + currentLevel +".png"); // loading the level
                loadImageLevel(level);
                levelLoaded = true;
            }
        }
    }
    
    private void render()
    {        
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
            
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;
                
        if(state == STATE.GAME)
        {
            //////////////////////////////////                       
            //Draw Here
                 
            g.setColor(Color.BLACK);
            g.fillRect(0,0, getWidth(), getHeight());
            
            g2d.translate( (-(Game.cam.getX() - Game.WIDTH/2)  < WIDTH/2? 0 : cam.getX()) , (-(Game.cam.getY() - Game.HEIGHT/2) <  Game.HEIGHT/2 ? 0 : cam.getY())); // begin of cam
                                    
            handler.render(g);     
            
            g.setColor(Color.white);           
            g.setFont(font);
            g.drawString("Time: "  + timeMinutes + ":" + (timeSeconds < 10 ? "0" + timeSeconds : timeSeconds), (int)(-(Game.cam.getX() - Game.WIDTH/2)  < WIDTH/2? 10 : (int)(-(Game.cam.getX() - Game.WIDTH/2) - (WIDTH/2) + 10)), (-(Game.cam.getY() - Game.HEIGHT/2) >  Game.HEIGHT/2) ?  (int)(-(Game.cam.getY() - 600)) : 600 );
            g.drawString("FPS: " + fps, (int)(-(Game.cam.getX() - Game.WIDTH/2)  < WIDTH/2? 10 : (int)(-(Game.cam.getX() - Game.WIDTH/2) - (WIDTH/2) + 10)), (-(Game.cam.getY() - Game.HEIGHT/2) <  Game.HEIGHT/2) ? 30 : (int)(-(Game.cam.getY() - 30)));
            g.drawString("Score: " + score,(int)(-(Game.cam.getX() - Game.WIDTH/2)  < WIDTH/2? 625 : (int)(-(Game.cam.getX() - 625))), (-(Game.cam.getY() - Game.HEIGHT/2) <  Game.HEIGHT/2) ? 30 : (int)(-(Game.cam.getY() - 30)));
            
            g2d.translate(-cam.getX() , -cam.getY()); //end of cam
            
            //////////////////////////////////
        }
        else if(state == STATE.MENU)
        {
            /////////////////////////////////
            //Draw here
          
            //BufferedImage bkg = new BufferedImage("/res/.png");
            //g.drawImage(, 0, 0, this);            
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            menu.render(g);
            
            /////////////////////////////////
        }
        else if(state == STATE.HELP)
        {
            /////////////////////////////////
            //Draw here
          
            //BufferedImage bkg = new BufferedImage("/res/.png");
            //g.drawImage(, 0, 0, this);            
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            help.render(g);
            
            /////////////////////////////////
        }
         else if(state == STATE.CONTROLS)
        {
            /////////////////////////////////
            //Draw here
          
            //BufferedImage bkg = new BufferedImage("/res/.png");
            //g.drawImage(, 0, 0, this);            
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            controls.render(g);
            
            /////////////////////////////////
        }
        else if(state == STATE.LOADING)
        {
            /////////////////////////////////
            //Draw here
          
            //BufferedImage bkg = new BufferedImage("/res/.png");
            //g.drawImage(, 0, 0, this);            
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            
            /////////////////////////////////
        }
        g.dispose();
        bs.show();        
    }           
    
    private void loadImageLevel(BufferedImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        
        scoreAddedThisLevel = 0;
        timeMinutes = 0;
        timeSeconds = 0;
        
        System.out.println("(width,height) = (" + w + "," + h + ")");  
        
        for(int row = 0; row < h; row++)
        {
            for(int col = 0; col < w; col++)
            {
                int pixel = image.getRGB(row,col);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                               
                if(red == 255 && green == 255 && blue == 0)
                {
                    handler.addObject(new Coin(row * 32, col * 32, ObjectId.Coin));
                }
                
                if(red == 150 && green == 150 && blue == 150)
                {
                    handler.addObject(new StillEnemy(row * 32, col * 32, ObjectId.Enemy));
                }
                
                if(red == 160 && green == 160 && blue == 160)
                {
                    handler.addObject(new MovingEnemy(row * 32, col * 32, false, 5, 1, ObjectId.Enemy));
                }
                
                if(red == 170 && green == 170 && blue == 170)
                {
                    handler.addObject(new MovingEnemy(row * 32, col * 32, true, 5, 2, ObjectId.Enemy));
                }
                
                if(red == 50 && green == 50 && blue == 50)
                {
                    handler.addObject(new MovingBlock(row * 32, col * 32, true, 5, 2, ObjectId.Block));
                }
                
                if(red == 75 && green == 75 && blue == 75)
                {
                    handler.addObject(new MovingBlock(row * 32, col * 32, false, 5, 2, ObjectId.Block));
                }
                
                if(red == 255 && green == 255 && blue == 255)
                {
                    handler.addObject(new Block(row * 32, col * 32, 0, ObjectId.Block));
                }
                
                if(red == 125 && green == 125 && blue == 125)
                {
                    handler.addObject(new Block(row * 32, col * 32, 1, ObjectId.Block));
                }
                
                if(red == 200 && green == 200 && blue == 200)
                {
                    handler.addObject(new Flag(row * 32, col*32, ObjectId.Flag));
                }
                
                if(red == 255 && green == 0 && blue == 0)
                {
                    handler.addObject(new Lava(row * 32, col * 32, 2, ObjectId.Lava));
                }
                
                if(red == 0 && green == 0 && blue == 255)
                {
                    handler.addObject(new Player(row * 32, col * 32, handler, ObjectId.Player));
                }
            }
        }
        
        levelLoaded = true;
        if(Game.state == STATE.LOADING)
        {
            state = STATE.GAME;
        }
    } 
    
    public static Texture getInstance()
    {
        return tex;
    }
    
    public synchronized void start()
    {
        if(running)
            return;
            
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public static void main(String args[])
    {    
        new SplashScreenDriver();                
    }
}
