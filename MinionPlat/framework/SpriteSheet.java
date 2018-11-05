package framework;


/**
 * Write a description of class SpriteSheet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage image;
    
    public SpriteSheet(BufferedImage image)
    {
        this.image = image;
    }
    
    public BufferedImage grabImage(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col* width) - width, (row * height) - height, width, height);
        return img;
    }
}
