package objects;


/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import framework.GameObject;
import framework.ObjectId;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import window.Handler;
import framework.GameObject;
import framework.Texture;
import window.Game;
import window.Animation;
import framework.STATE;

public class Player extends GameObject
{
    private float width = 48, height = 96;
    
    private float gravity = 0.15f;
    private final float MAX_SPEED = 10;
    
    private boolean alive = false;
    public boolean playerMoving = false;
    
    private Handler handler;
    
    Texture tex = Game.getInstance();
    
    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerIdle;
    
    public Player(float x, float y, Handler handler,ObjectId id)
    {
        super(x,y,id);
        this.handler = handler;
        alive = true;
        
        playerIdle = new Animation(10, tex.player[0], tex.player[1]);
        playerWalkLeft = new Animation(7, tex.player[6], tex.player[7], tex.player[8], tex.player[9]);
        playerWalkRight = new Animation(7, tex.player[2], tex.player[3], tex.player[4], tex.player[5]);
    }
    
    @Override
    public void tick(LinkedList<GameObject> object)
    {
        x += velX;
        y += velY;
                       
        //System.out.println("Player Position: (" + getX() + "," + getY() + ")");
        
        if(!isAlive())
        {    
            object.clear();
            Game.score -= Game.scoreAddedThisLevel;
            Game.scoreAddedThisLevel = 0;
            Game.levelLoaded = false;
            Game.state = STATE.MENU;
        }
            
        if(falling || jumping)
        {
            velY += gravity;
            
            if(velY > MAX_SPEED)
            {
                velY = MAX_SPEED;
            }
        }
        
        Collision(object);
        
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
        playerIdle.runAnimation();
    }
    
    public void render(Graphics g)
    {               
        //g.setColor(Color.blue);
        if(velX > 0)
        {
            playerWalkRight.drawAnimation(g, (int)x, (int)y, 48, 96);
        }
        else if(velX < 0)
        {
            playerWalkLeft.drawAnimation(g, (int)x, (int)y, 48, 96);
        }
        else
        {
            //g.drawImage(tex.player[0], (int)x, (int)y, null);
            playerIdle.drawAnimation(g, (int)x, (int)y, 48, 96);
        }
        
        /* PLAYER BOUNDS
        Graphics2D g2d = (Graphics2D) g;
              
        g2d.setColor(Color.red);
        
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
        */
    }
    
    private void Collision(LinkedList<GameObject> object)
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ObjectId.Flag)
            {
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    if(Game.currentLevel + 1 <= Game.MAX_LEVEL)
                    {
                        Game.currentLevel++;
                        object.clear();
                        Game.levelLoaded = false;
                        Game.state = STATE.LOADING;
                    }
                    else
                    {                        
                       Game.currentLevel = 1; 
                       object.clear();
                       Game.levelLoaded = false;
                       Game.state = STATE.LOADING;
                    }
                }
                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    if(Game.currentLevel + 1 <= Game.MAX_LEVEL)
                    {
                        Game.currentLevel++;
                        object.clear();
                        Game.levelLoaded = false;
                        Game.state = STATE.LOADING;
                    }
                    else
                    {
                        Game.currentLevel = 1; 
                        object.clear();
                        Game.levelLoaded = false;
                        Game.state = STATE.LOADING;
                    }
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    if(Game.currentLevel + 1 <= Game.MAX_LEVEL)
                    {
                        Game.currentLevel++;
                        object.clear();
                        Game.levelLoaded = false;
                        Game.state = STATE.LOADING;
                    }
                    else
                    {
                        Game.currentLevel = 1; 
                        object.clear();
                        Game.levelLoaded = false;
                        Game.state = STATE.LOADING;
                    }
                }
                
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    if(Game.currentLevel + 1 <= Game.MAX_LEVEL)
                    {
                        Game.currentLevel++;
                        object.clear();
                        Game.levelLoaded = false;               
                        Game.state = STATE.LOADING;
                    }
                    else
                    {
                        Game.currentLevel = 1; 
                        object.clear();
                        Game.levelLoaded = false;
                        Game.state = STATE.LOADING;
                    }
                }
            }
            
            if(tempObject.getId() == ObjectId.Coin)
            {
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    object.remove(tempObject);
                    Game.score += 10;
                    Game.scoreAddedThisLevel += 10;
                }
                
                //Bottom
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    object.remove(tempObject);
                    Game.score += 10;
                    Game.scoreAddedThisLevel += 10;
                }
                
                //Right
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    object.remove(tempObject);
                    Game.score += 10;
                    Game.scoreAddedThisLevel += 10;
                }
                
                //Left
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    object.remove(tempObject);
                    Game.score += 10;
                    Game.scoreAddedThisLevel += 10;
                }
            }
            
            if(tempObject.getId() == ObjectId.Enemy)
            {
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    setAlive(true);
                }
                
                //Bottom
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    setAlive(true);
                }
                
                //Right
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    setAlive(false);
                }
                
                //Left
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    setAlive(false);
                }
            }
            
            if(tempObject.getId() == ObjectId.Lava)
            {
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    setAlive(false);
                }
                
                //Bottom
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    setAlive(false);
                }
                
                //Right
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    setAlive(false);
                }
                
                //Left
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    setAlive(true);
                }
            }
            
            if(tempObject.getId() == ObjectId.Block)
            {
                //Top
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    y = tempObject.getY() + (height / 3);
                    velY = 0;
                }
                
                //Bottom
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    if(tempObject instanceof MovingBlock)
                    {
                        //System.out.println("is a moving block");
                        if(!((MovingBlock)tempObject).isVertical)
                        {
                            //System.out.println("horizontal");
                            if(((MovingBlock)tempObject).moving)
                            {
                                //System.out.println("moving");
                                if(!playerMoving)
                                {
                                    setVelX(tempObject.getVelX());
                                }
                            }
                            else
                            {
                                if(!playerMoving)
                                    setVelX(0);
                            }
                        }
                    }
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else
                {
                    falling = true;
                }
                
                //Right
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    x = tempObject.getX() - width;
                }
                
                //Left
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    x = tempObject.getX() + width * .71f;
                }
            }
        }
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) ((int) x + (width/2) - ((width/2)/2)), (int) ((int) y + (height/2)), (int) width/2, (int) height/2);
    }
    
    public Rectangle getBoundsTop()
    {
        return new Rectangle((int) ((int) x + (width/2) - ((width/2)/2)), (int) y , (int) width/2, (int) height/2);
    }
    
    public Rectangle getBoundsRight()
    {
        return new Rectangle((int) ((int) x + (width-5)), (int) y + 5, (int) 5, (int) height - 10);
    }
    
    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
    }  
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
}
