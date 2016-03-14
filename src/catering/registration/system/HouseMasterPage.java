package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HouseMasterPage {

    static JFrame HMFrame = new JFrame("Housemaster Page");
    static JPanel HMPanel = new JPanel();
    static JButton AddUser = new JButton("Add User");
    static JButton EditUser = new JButton("Edit User");
    static JButton DeleteUser = new JButton("Delete User");
    static JButton ViewStats = new JButton("View Statistics");
    static JButton LogoutButton = new JButton("Log Out");
    static JButton Add_Program_User = new JButton("Add Program User");
    static JButton Delete_Program_User = new JButton("Delete Program User");
    public static String House = "";

    public void main() {


        HMFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HMPanel.setLayout(null);
        HMFrame.add(HMPanel);
        HMFrame.setSize(800, 600);
        HMPanel.add(AddUser);
        HMPanel.add(EditUser);
        HMPanel.add(DeleteUser);
        HMPanel.add(Add_Program_User);
        HMPanel.add(Delete_Program_User);
        HMPanel.add(ViewStats);
        HMPanel.add(LogoutButton);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        AddUser.setFont(font);
        EditUser.setFont(font);
        DeleteUser.setFont(font);
        ViewStats.setFont(font);
        LogoutButton.setFont(font);
        Add_Program_User.setFont(font);
        Delete_Program_User.setFont(font);
        HMFrame.setVisible(true);
        HMPanel.setLayout(null);
        HMFrame.setLocationRelativeTo(null);


        POS_ITEM(AddUser, HMPanel, 300, 120, 200, 50);
        POS_ITEM(EditUser, HMPanel, 300, 190, 200, 50);
        POS_ITEM(DeleteUser, HMPanel, 300, 260, 200, 50);
        POS_ITEM(ViewStats, HMPanel, 300, 330, 200, 50);
        POS_ITEM(LogoutButton, HMPanel, 300, 20, 200, 50);
        POS_ITEM(Add_Program_User, HMPanel, 300, 400, 200, 50);
        POS_ITEM(Delete_Program_User, HMPanel, 300, 470, 200, 50);

        if (House.equalsIgnoreCase("admin")) {
            Add_Program_User.setVisible(true);
            Delete_Program_User.setVisible(true);
            House = "none";
        } else {
            Add_Program_User.setVisible(false);
            Delete_Program_User.setVisible(false);
        }

        LogoutButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                CateringRegistrationSystem.LoginFrame.setVisible(true);
                HMFrame.dispose();
            }
        });

        AddUser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                AddUserPage NextWindow = new AddUserPage();
                NextWindow.main();
                HMFrame.setVisible(false);
            }
        });

        EditUser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                EditUserPage.House = House;
                EditUserPage.run();
                HMFrame.setVisible(false);
            }
        });

        DeleteUser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                DeleteUserPage.Delete();
                DeleteUserPage.House = House;
                HMFrame.setVisible(false);
            }
        });

        ViewStats.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String ViewStats[] = new String[0];
                CateringStaffPage.main(ViewStats);
                CateringStaffPage.ButtonOut = "";
                CateringStaffPage.House = House;
            }
        });

        Add_Program_User.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                AddProgramPage NextWindow = new AddProgramPage();
                NextWindow.main();
                HMFrame.setVisible(false);
            }
        });

        Delete_Program_User.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                DeleteProgramPage NextWindow = new DeleteProgramPage();
                NextWindow.main();
                HMFrame.setVisible(false);
            }
        });

    }

    public void actionperformed(ActionEvent event) {
    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int Width, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, Width, Height);
    }
}