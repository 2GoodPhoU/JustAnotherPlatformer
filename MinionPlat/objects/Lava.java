package objects;


/**
 * Write a description of class Lava here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import framework.*; 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import window.Game;

public class Lava extends GameObject
{    
    Texture tex = Game.getInstance();
    private int type;
    
    public Lava(float x, float y, int type, ObjectId id)
    {
        super(x,y,id);
        this.type = type;
    }
    
    public void tick(LinkedList<GameObject> object)
    {
        
    }
    
    public void render(Graphics g)
    {
        if(type == 2)
        {
            g.drawImage(tex.block[2], (int)x, (int)y, null);
        }
        if(type == 3)
        {
            g.drawImage(tex.block[3], (int)x, (int)y, null);
        }
        if(type == 4)
        {
            g.drawImage(tex.block[4], (int)x, (int)y, null);
        }
        if(type == 4)
        {
            g.drawImage(tex.block[5], (int)x, (int)y, null);
        }
    }
            
    public Rectangle getBounds()
    {
        return new Rectangle((int) x,(int) y,32,32);
    }    
}
