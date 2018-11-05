package window;


/**
 * Write a description of class Loader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JWindow;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import framework.Texture;
import window.Game;
import java.awt.Color;
import javax.swing.SwingUtilities;
import java.awt.Graphics;

public class Loader extends JWindow
{
    private BorderLayout borderLayout;
    private JLabel imgLabel;
    private JPanel southPanel;
    private FlowLayout southFlow;
    private JProgressBar progressBar;
    private ImageIcon imgIcon;
    private Texture tex = Game.getInstance();
    private Animation loading;
    private boolean isLoading = false;
        
    public Loader()
    {     
        this.imgIcon = new ImageIcon(tex.loadingAnimation[1]);
        borderLayout = new BorderLayout();
        imgLabel = new JLabel();
        southPanel = new JPanel();
        southFlow = new FlowLayout();
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        isLoading = true;
        loading = new Animation(60, tex.loadingAnimation);
        try
        {
            init();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void init() throws Exception
    {
        imgLabel.setIcon(imgIcon);
        getContentPane().setLayout(borderLayout);
        southPanel.setLayout(southFlow);
        southPanel.setBackground(Color.black);
        getContentPane().add(imgLabel, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);
        southPanel.add(progressBar, null);
        pack();
    }
    
    public void setMaxProgress(int maxProgress)
    {
        progressBar.setMaximum(maxProgress);
    }
    
    public void setProgress(int progress)
    {
        double percentage = (progress / progressBar.getMaximum()) * 100;                
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                progressBar.setValue(progress);
                progressBar.setString("Loading: " + (int) percentage + "%");
            }
        });
    } 
    
    public void render(Graphics  g)
    {
        if(isLoading)
        {
            loading.drawAnimation(g, (int)Game.WIDTH/2, (int)Game.HEIGHT/2, 4, 4);
        }
    }    
}
