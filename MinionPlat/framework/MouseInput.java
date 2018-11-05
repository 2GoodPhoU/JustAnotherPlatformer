package framework;

/**
 * Write a description of class MouseInput here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import window.Game;
import window.Menu;
import window.Help;
import window.Controls;
import framework.STATE;
import java.awt.Graphics;
import java.awt.Color;

public class MouseInput extends MouseAdapter
{        
    private Menu menu;
    private Help help;
    private Controls controls;
    private Graphics g;
    
    public MouseInput(Menu menu, Help help, Controls controls)
    {
        this.menu = menu;
        this.help = help;
        this.controls = controls;
    }
    
    public void mousePressed(MouseEvent e)
    {
        if(Game.state == STATE.MENU)
        {
            if(menu.playButton.contains(e.getPoint()))
            {
                Game.state = STATE.GAME;
            }
            else if(menu.helpButton.contains(e.getPoint()))
            {
                Game.state = STATE.HELP;
            }
            else if(menu.quitButton.contains(e.getPoint()))
            {
                System.exit(1);
            }
        }
        else
        if(Game.state == STATE.HELP)
        {
            if(help.controlsButton.contains(e.getPoint()))
            {
                Game.state = STATE.CONTROLS;
            }
            else if(help.soundButton.contains(e.getPoint()))
            {
                
            }
            else if(help.menuButton.contains(e.getPoint()))
            {
                Game.state = STATE.MENU;
            }
        }
        else
        if(Game.state == STATE.CONTROLS)
        {
            if(controls.helpButton.contains(e.getPoint()))
            {
                Game.state = STATE.HELP;
            }
            else if(help.soundButton.contains(e.getPoint()))
            {
                
            }
            else if(help.menuButton.contains(e.getPoint()))
            {
                Game.state = STATE.MENU;
            }
        }
    }   
    
    public void mouseEntered(MouseEvent e)
    {
        if(Game.state == STATE.MENU)
        {
           
        }
    }
    
    public void mouseExited(MouseEvent e)
    {
        if(Game.state == STATE.MENU)
        {
            
        }
    }
}
