package window;


/**
 * Write a description of class Handler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.LinkedList;
import framework.GameObject;
import java.awt.Graphics;
import framework.ObjectId;
import objects.Block;

public class Handler
{
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    private GameObject tempObject;
    public static int objectsRendered = 0;
    
    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            tempObject = object.get(i);
            if (Game.cam != null) 
            {
                if(tempObject.getX() > (-(Game.cam.getX() - Game.WIDTH/2) - 480) && tempObject.getX() < (-(Game.cam.getX() - Game.WIDTH/2) + (-(Game.cam.getX() - Game.WIDTH/2)  < Game.WIDTH/2 ? 736 : 480) ))
                { 
                    if(tempObject.getY() > -(Game.cam.getY() - Game.HEIGHT/2) - 384 && tempObject.getY() > -(Game.cam.getY() - Game.HEIGHT/2) - (-(Game.cam.getY() - Game.HEIGHT/2) <  Game.HEIGHT/2 ? 480 : 384))
                    {
                        tempObject.tick(object);
                    }
                }       
            }
            
        }
    }
    
    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {                   
            tempObject = object.get(i);
            if (Game.cam != null)
            {
                if(tempObject.getX() > (-(Game.cam.getX() - Game.WIDTH/2) - 480) && tempObject.getX() < (-(Game.cam.getX() - Game.WIDTH/2) + (-(Game.cam.getX() - Game.WIDTH/2)  < Game.WIDTH/2 ? 736 : 480) ))
                { 
                    if(tempObject.getY() > -(Game.cam.getY() - Game.HEIGHT/2) - 384 && tempObject.getY() > -(Game.cam.getY() - Game.HEIGHT/2) - (-(Game.cam.getY() - Game.HEIGHT/2) <  Game.HEIGHT/2 ? 480 : 384))
                    {
                        //System.out.println(tempObject.getX());
                        //System.out.println(-(Game.cam.getX() - Game.WIDTH/2));
                        tempObject.render(g);
                        objectsRendered++;
                    }
                } 
            }
                
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
    
    /*
    public void createLevel()
    {
        for(int p = (int) (Game.WIDTH * .33); p < (int) (Game.WIDTH * .66); p += 32)
        {
            addObject(new Block(p, 425, ObjectId.Block));
        }
        for(int yy = 0; yy < Game.HEIGHT - 64; yy +=32)
        {
            addObject(new Block(0,yy, ObjectId.Block));
        }
        for(int xx = 0; xx < Game.WIDTH * 2; xx+=32)
        {
            addObject(new Block(xx,Game.HEIGHT-32, ObjectId.Block));
        }
    }
    */
}
