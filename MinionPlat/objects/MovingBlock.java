package objects;


/**
 * Write a description of class MovingBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;
import framework.ObjectId;
import framework.GameObject;

public class MovingBlock extends GameObject
{
    private int width = 96, height = 32;
    private int moveLength; //in blocks
    private int speed;
    private int ticks = 0;
    int dir = 1; // 1 = right or down , -1 = left or up
    private float startingXPos, startingYPos;
    boolean isVertical;  
    boolean moving;
    
    public MovingBlock(float x, float y, boolean isVertical, int moveLength, int speed, ObjectId id)
    {
        super(x,y,id);
        this.moveLength = moveLength;
        this.speed = speed;
        this.isVertical = isVertical;
        moving = false;
        startingXPos = x;
        startingYPos = y;        
    }
    
    public void tick(LinkedList<GameObject> object)
    {
        x += velX;
        y += velY;
        
        if(isVertical)// going vertical
        {
            if(getY() <= startingYPos)
            {
                if(moving)
                {
                    setVelY(0);
                    setY(startingYPos);
                    moving = false;
                }  
                ticks++;
                if(ticks > 90)
                {
                   ticks = 0; 
                   setVelY(speed);                   
                   moving = true;
                }
            }
            if(getY() >= startingYPos + (moveLength * 32))
            {
                if(moving)
                {
                    setVelY(0);
                    setY(startingYPos + moveLength * 32);
                    moving = false;
                }
                ticks++;
                if(ticks > 90)
                {
                    ticks = 0;
                    dir = -1;
                    setVelY((speed * -1));
                    moving = true;
                }
            }
        }
        else //going horizontal
        {
            if(getX() <= startingXPos)
            {
                if(moving)
                {
                    setVelX(0);
                    setX(startingXPos);
                    moving = false;
                } 
                ticks++;
                if(ticks > 90)
                {                   
                   ticks = 0; 
                   dir = 1;
                   setVelX(speed);
                   moving = true;
                }
            }
            if(getX() >= startingXPos + (moveLength * 32))
            {
                if(moving)
                {
                    setVelX(0);
                    setX(startingXPos + moveLength * 32);
                    moving = false;
                }
                ticks++;
                if(ticks > 90)
                {
                    ticks = 0;
                    dir = -1;
                    setVelX((speed * -1));
                    moving = true;
                }
            }
        }
    }
    
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, width, height);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle((int)getX(), (int)getY(), width, height);
    }
}
