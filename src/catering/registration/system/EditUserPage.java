package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EditUserPage {

    static JFrame UserFrame = new JFrame("Edit User Page");
    static JPanel UserPanel = new JPanel();
    static JButton Back = new JButton("Back");
    static JButton Edit = new JButton("Edit Record");
    static JComboBox List = new JComboBox();
    public static String House;

    public static void run() {
        Edit();
    }

    public void main() {
        Edit();
    }

    public static void Edit() {

        UserFrame.add(UserPanel);
        UserPanel.add(Back);
        UserPanel.add(Edit);
        UserPanel.add(List);
        UserPanel.setLayout(null);
        UserFrame.setSize(800, 600);
        UserFrame.setVisible(true);
        UserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserFrame.setLocationRelativeTo(null);
        UserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserFrame.setLocationRelativeTo(null);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        Back.setFont(font);
        Edit.setFont(font);

        Edit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                String SelectedName = List.getSelectedItem().toString();

                if (Database.Users.containsKey(SelectedName)) {

                    String id = Database.Users.get(SelectedName).toString();
                    Database.getRecord(id);

                } else {

                    JOptionPane.showMessageDialog(UserFrame, "User Not Found");
                }
            }
        });

        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                HouseMasterPage.HMFrame.setVisible(true);
                UserFrame.dispose();
            }
        });


        POS_ITEM(Back, UserPanel, 200, 300, 150, 50);
        POS_ITEM(List, UserPanel, 200, 150, 350, 50);
        POS_ITEM(Edit, UserPanel, 400, 300, 150, 50);

        Database.GetNames(House);

    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int Width, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, Width, Height);
    }
}