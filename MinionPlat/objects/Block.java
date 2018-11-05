package objects;


/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import framework.GameObject;
import java.awt.Color;
import java.util.LinkedList;
import framework.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import framework.Texture;
import window.Game;

public class Block extends GameObject
{
    Texture tex = Game.getInstance();
    private int type;
    
    public Block(float x, float y, int type, ObjectId id)
    {
        super(x,y,id);
        this.type = type;
    }
    
    @Override
    public void tick(LinkedList<GameObject> object)
    {
        
    }
    
    public void render(Graphics g)
    {
       if(type == 0)
       {
           g.drawImage(tex.block[0], (int)x, (int)y, null);
       }
        if(type == 1)
       {
           g.drawImage(tex.block[1], (int)x, (int)y, null);
       }
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
