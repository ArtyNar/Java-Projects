/**
 * @author Artem Kova
 * @date 3/29/2022
 * @class 3230 Java
 */

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class BBallGUI extends JFrame
{
    private Image icon; 

    public BBallGUI()
    {
        setTitle("BBall");

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        // Just puts the window in the middle of screen
        setLocation(200, 100);
        setResizable(false);

        setSize(new Dimension(1200,400));

        icon = new ImageIcon("lib/peng.png").getImage();
        setIconImage(icon);

        Container pane = this.getContentPane();

        BBallPanel mainPanel = new BBallPanel(menuBar);

        pane.add(mainPanel);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
