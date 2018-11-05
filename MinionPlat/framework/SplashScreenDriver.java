package framework;


/**
 * Write a description of class SplashScreenDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import window.Loader;
import window.Game;
import window.Window;
import java.awt.image.BufferedImage;
import window.BufferedImageLoader;
import objects.*;
import window.Handler;

public class SplashScreenDriver
{
    private Window window;
    private Loader screen;
          
    public SplashScreenDriver()
    {
        screen = new Loader();
        screen.setLocationRelativeTo(null);
        screen.setMaxProgress(100);
        screen.setVisible(true);
        
        //////////////////////////////
        //WHAT YOU ARE LOADING
        
        window = new Window(800, 600, "Platformer Game", new Game());
        int i = 0;
        while(Window.loaded = false)
        {                             
           screen.setProgress(i++);
        }        
          
        /*
        for(; i < 100; i++)
        {
            for(int j = 0; j < 500000; j++)
            {
                String t = "ewf" + (i + j);
            }
            screen.setProgress(i);
        }        
        */
       
        screen.setProgress(100);
        
        //////////////////////////////
        screen.setVisible(false);
        window.frame.setVisible(true);
    }           
}
