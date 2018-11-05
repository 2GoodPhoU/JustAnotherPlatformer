package window;


/**
 * Write a description of class Window here.
 * 
 * @author Alex Thomas
 * @version January 22, 2017
 */

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window
{
    public static boolean loaded = false;
    public JFrame frame;
    
    public Window(int w, int h, String title, Game game)
    {
        game.setPreferredSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMinimumSize(new Dimension(w, h));
        
        frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        loaded = true;
        //frame.setVisible(true);
        
        game.start();
    }
}
