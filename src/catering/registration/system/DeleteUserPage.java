package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DeleteUserPage extends Database {

    static JFrame UserFrame = new JFrame("Delete User Page");
    static JPanel UserPanel = new JPanel();
    static JButton Back = new JButton("Back");
    static JButton Delete = new JButton("Delete Record");
    static JComboBox List = new JComboBox();
    public static String House = "none";

    public void main() {
        Delete();
    }

    public static void Delete() {

        UserFrame.add(UserPanel);
        UserPanel.add(Back);
        UserPanel.add(Delete);
        UserPanel.add(List);
        UserFrame.setSize(800, 600);
        UserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserFrame.setLocationRelativeTo(null);
        UserPanel.setLayout(null);
        UserFrame.setVisible(true);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        Back.setFont(font);
        Delete.setFont(font);

        Delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                String SelectedName = List.getSelectedItem().toString();

                if (Users.containsKey(SelectedName)) {

                    String id = Users.get(SelectedName).toString();
                    DeleteUser(id);
                    JOptionPane.showMessageDialog(UserFrame, "Deleted User");
                    HouseMasterPage.HMFrame.setVisible(true);
                    UserFrame.dispose();
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
        POS_ITEM(Delete, UserPanel, 400, 300, 150, 50);


        GetNames(House);

    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int Width, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, Width, Height);
    }
}