package framework;


/**
 * Write a description of class KeyInput here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import window.Handler;
import window.Game;
import framework.STATE;
import objects.Player;

public class KeyInput extends KeyAdapter
{
    Handler handler;
    
    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(Game.state == STATE.GAME)
                if(tempObject.getId() == ObjectId.Player)
                {
                    if(key == KeyEvent.VK_D) 
                    {
                        tempObject.setVelX(5);
                        ((Player)tempObject).playerMoving = true;
                    }
                    if(key == KeyEvent.VK_A) 
                    {
                        tempObject.setVelX(-5);
                        ((Player)tempObject).playerMoving = true;
                    }
                    if(key == KeyEvent.VK_SPACE && !tempObject.jumping)
                    {
                        tempObject.setJumping(true);
                        tempObject.setVelY(-6);                     
                    }
                }
        }
        
        if(Game.state == STATE.GAME)
        {
            if(key == KeyEvent.VK_ESCAPE)
            {
                Game.state = STATE.MENU;
            }
        }else if(Game.state == STATE.MENU)
        {
            if(key == KeyEvent.VK_ESCAPE)
            {
                Game.state = STATE.GAME;
            }
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ObjectId.Player)
            {
                if(key == KeyEvent.VK_D) 
                {
                    tempObject.setVelX(0);
                    ((Player)tempObject).playerMoving = false;
                }
                if(key == KeyEvent.VK_A) 
                {
                    tempObject.setVelX(0);
                    ((Player)tempObject).playerMoving = false;
                }
            }
        }
    }
    
}
