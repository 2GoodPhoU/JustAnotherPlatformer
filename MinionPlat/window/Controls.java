package window;


/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Controls
{
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 3 + 25, 150, 200, 75);
    public Rectangle soundButton = new Rectangle(Game.WIDTH / 3 + 25, 250, 200, 75);
    public Rectangle menuButton = new Rectangle(Game.WIDTH / 3 + 25, 350, 200, 75);
    
    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Controls", (Game.WIDTH / 3) + 27, 100);        
        
        Font fnt1 = new Font("arial", Font.BOLD, 60);
        g.setFont(fnt1);
        g.drawString("Help", (int) helpButton.getX() + 40, (int) helpButton.getY() + 60);
        g.drawString("Sound", (int) soundButton.getX() + 7, (int) soundButton.getY() + 60);
        g.drawString("Menu", (int) menuButton.getX() + 25, (int) menuButton.getY() + 60);        
        g2d.draw(helpButton);
        g2d.draw(soundButton);
        g2d.draw(menuButton);
    }
}
