package window;


/**
 * Write a description of class BufferedImageLoader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BufferedImageLoader
{
    private BufferedImage image;
    
    public BufferedImage loadImage(String path)
    {
        try
        {
            image = ImageIO.read(getClass().getResource(path));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
}
