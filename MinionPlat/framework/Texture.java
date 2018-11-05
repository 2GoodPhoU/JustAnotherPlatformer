package framework;


/**
 * Write a description of class Texture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import window.BufferedImageLoader;
import java.io.IOException;

public class Texture
{
    SpriteSheet bs;
    SpriteSheet ps;
    SpriteSheet ls;
    
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage loading_sheet = null;
    
    public BufferedImage[] block = new BufferedImage[3];
    public BufferedImage[] player = new BufferedImage[10];
    public BufferedImage[] coin = new BufferedImage[3];
    public BufferedImage[] enemy = new BufferedImage[2];
    public BufferedImage[] loadingAnimation = new BufferedImage[60];
    
    public Texture()
    {
        BufferedImageLoader loader = new BufferedImageLoader();

        
        block_sheet = loader.loadImage("/res/BlockSheetTest.png");
        player_sheet = loader.loadImage("/res/player_sheet.png");
        loading_sheet = loader.loadImage("/res/loading_sheet.png");
        
        ps = new SpriteSheet(player_sheet);
        bs = new SpriteSheet(block_sheet);
        ls = new SpriteSheet(loading_sheet);
        getTextures();
    }
    
    private void getTextures()
    {
        block[0] = bs.grabImage(1,1,32,32);
        block[1] = bs.grabImage(2,1,32,32);
        block[2] = bs.grabImage(3,1,32,32);
        player[0] = ps.grabImage(1,1,48,96);
        player[1] = ps.grabImage(2,1,48,96);
        player[2] = ps.grabImage(3,1,48,96);
        player[3] = ps.grabImage(4,1,48,96);
        player[4] = ps.grabImage(5,1,48,96);
        player[5] = ps.grabImage(6,1,48,96);
        player[6] = ps.grabImage(7,1,48,96);
        player[7] = ps.grabImage(8,1,48,96);
        player[8] = ps.grabImage(9,1,48,96);
        player[9] = ps.grabImage(10,1,48,96);
        coin[0] = bs.grabImage(1,2,32,32);
        coin[1] = bs.grabImage(2,2,32,32);
        coin[2] = bs.grabImage(3,2,32,32);
        enemy[0] = bs.grabImage(4,1,32,32);
        enemy[1] = bs.grabImage(4,1,32,32);
        //loadingAnimation[0] = ls.grabImage(0,0,60,60);        
        for(int row = 1; row <= 9; row++)
        {
            for(int col = 1; col <= 9; col++ )
            {
                if((row * 8) + col < 60)
                    loadingAnimation[((row - 1) * 8) + col] = ls.grabImage(col,row, 6, 6);
            }
        }
    }
}
