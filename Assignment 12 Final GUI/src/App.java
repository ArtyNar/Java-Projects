/**
 * @author Artem Kova
 * @date 3/29/2022
 * @class 3230 Java
 */

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

public class App {
    public static void main(String[] args) throws Exception {

        EventQueue.invokeLater( () ->
            {
                BBallGUI gui = new BBallGUI();
            }
        );      

    }
}
