package objects;

import framework.GameObject;
import java.awt.Color;
import java.util.LinkedList;
import framework.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import framework.Texture;
import window.Game;


/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import window.Animation;

public class Coin extends GameObject
{
   Texture tex = Game.getInstance();
   private int type;
   
   private Animation coinFlip;
   
   public Coin(float x, float y, ObjectId id)
   {
        super(x,y,id);
        this.type = type;
        coinFlip = new Animation(10, tex.coin);
   }    
   
   @Override
   public void tick(LinkedList<GameObject> object)
   {
       coinFlip.runAnimation();
   }
   
   public void render(Graphics g)
   {
       coinFlip.drawAnimation(g, (int)x, (int)y, 32, 32);
   }
    
   @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
