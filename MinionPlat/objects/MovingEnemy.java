package objects;


/**
 * Write a description of class MovingEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import java.util.LinkedList;

import window.Game;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class MovingEnemy extends GameObject
{
    private int width = 32, height = 32;
    private int moveLength; //In Blocks
    private int speed;
    private float startingXPos, startingYPos;   
    private boolean isVertical;
    
    private Texture tex = Game.getInstance();
    
    public MovingEnemy(float x, float y, boolean isVertical, int moveLength, int speed, ObjectId id)
    {
        super(x,y,id);
        this.isVertical = isVertical;
        this.moveLength = moveLength; 
        this.speed = speed;
        startingXPos = x;
        startingYPos = y;
    }
    
    public void tick(LinkedList<GameObject> object)
    {
        x += velX;
        y += velY;
        
        if(isVertical) // moving vertical
        {
            if(getY() <= startingYPos)
            {
                setVelY(speed);
            }
            if(getY() >= (startingYPos + (moveLength * 32)))
            {
                setVelY(-speed);
            }
        }
        else // moving horizontal
        {
            if(getX() <= startingXPos)
            {
                setVelX(speed);
            }
            if(getX() >= (startingXPos + (moveLength * 32)))
            {
                setVelX(-speed);
            }
        }
    }
    
    public void render(Graphics g)
    {
        g.drawImage(tex.enemy[1], (int)x, (int)y, null);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
