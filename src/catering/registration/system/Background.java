package catering.registration.system;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class Background {

    static JFrame frame = new JFrame("My Window");
    static JTextField txt = new JTextField(25);
    static public Image background = null;
    static JPanel panel = new javax.swing.JPanel() {

        public void paintComponent(Graphics g) { //Used to 'paint' the panel with an image
            try {
                background = ImageIO.read(new File("/Volumes/ikhalfan/IB_Java/Imran Khalfan/School Logo.jpg")); //Get the image from the link
            } catch (Exception ex) {
            }

            g.drawImage(background, 0, 0, this); //Add the image started at 0,0
        }
    };

    public static void main(String[] args) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(txt);
        frame.add(panel);
        frame.setSize(1440, 900);
        frame.setVisible(true);
        panel.setLayout(null);
        ObjectLocation(txt, 20, 140);
        frame.setLocationRelativeTo(null);  //Center window.
        frame.setResizable(false); //Dont Allow Resizing of Window
    }

    public static void ObjectLocation(Component MyObject, int left, int top) {
        Insets insets = panel.getInsets();
        Dimension size = MyObject.getPreferredSize();
        MyObject.setBounds(left + insets.left, top + insets.top, size.width, size.height);
    }
}