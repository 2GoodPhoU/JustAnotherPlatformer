package objects;


/**
 * Write a description of class StillEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Graphics2D;
import window.Handler;
import framework.GameObject;
import framework.Texture;
import window.Game;
import window.Animation;
import framework.STATE;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import java.util.LinkedList;

import window.Game;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class StillEnemy extends GameObject
{
    private int width = 32, height = 32;
    private boolean alive = false;
    private Texture tex = Game.getInstance();
    private Handler handler;
    
    public StillEnemy(float x, float y, ObjectId id)
    {
        super(x,y,id);
        //this.handler = handler;
        alive = true;
    }
    
    @Override
    public void tick(LinkedList<GameObject> object)
    {
 
        Collision(object);
        
    }
    
    private void Collision(LinkedList<GameObject> object)
    {
        if(object != null)
            for(int i = 0; i < object.size(); i++)
            {
                GameObject tempObject = object.get(i);
                
                if(tempObject.getId() == ObjectId.Player)
                {
                    if(getBoundsTop().intersects(tempObject.getBounds())) //top
                    {
                        object.remove(this);
                        Game.score += 50;
                        Game.scoreAddedThisLevel += 50;
                        
                    }
    
                }
                
            }
    }
    
    public void render(Graphics g)
    {
        g.drawImage(tex.enemy[0], (int)x, (int)y, null);  
        
        Graphics2D g2d = (Graphics2D)g;
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    
     public Rectangle getBoundsTop()
    {
        return new Rectangle((int) ((int) x + (width/2) - ((width/2)/2)), (int) y , (int) width/2, (int) height/2);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
