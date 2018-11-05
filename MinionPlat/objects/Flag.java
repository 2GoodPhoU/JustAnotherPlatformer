package objects;


/**
 * Write a description of class Flag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import framework.GameObject;
import framework.ObjectId;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Color;

public class Flag extends GameObject
{
    private int width = 32, height = 32;
    
    public Flag(float x, float y, ObjectId id)
    {
        super(x,y,id);
    }
    
    public void render(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRect((int)getX(), (int)getY(), width, height);
    }
    
    public void tick(LinkedList<GameObject> object)
    {
        
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle((int) getX(), (int) getY(), width, height);
    }
}
