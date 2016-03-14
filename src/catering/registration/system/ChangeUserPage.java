package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChangeUserPage {

    public static JFrame UserFrame = new JFrame("Edit User Page");
    public static JPanel UserPanel = new JPanel();
    public static JButton Back = new JButton("Back");
    public static JButton Change = new JButton("Change");
    public static JTextField ID = new JTextField();
    public static JTextField Name = new JTextField();
    public static JTextField Surname = new JTextField();
    public static JTextField Year = new JTextField();
    public static JTextField House = new JTextField();
    public static JTextField BoardingStatus = new JTextField();
    public static JLabel LabelID = new JLabel("ID");
    public static JLabel LabelName = new JLabel("Name");
    public static JLabel LabelSurname = new JLabel("Surname");
    public static JLabel LabelYear = new JLabel("Year");
    public static JLabel LabelHouse = new JLabel("House");
    public static JLabel LabelBoardingStatus = new JLabel("Boarding Status");
    public static String UserID;

    public static void main(String[] args) {
        window();
    }

    public static void window() {

        UserFrame.add(UserPanel);
        UserPanel.add(Back);
        UserPanel.add(Change);
        UserPanel.add(ID);
        UserPanel.add(Name);
        UserPanel.add(Surname);
        UserPanel.add(Year);
        UserPanel.add(House);
        UserPanel.add(BoardingStatus);
        UserPanel.add(LabelID);
        UserPanel.add(LabelName);
        UserPanel.add(LabelSurname);
        UserPanel.add(LabelYear);
        UserPanel.add(LabelHouse);
        UserPanel.add(LabelBoardingStatus);
        UserFrame.setSize(800, 600);
        UserFrame.setVisible(true);
        UserPanel.setLayout(null);
        UserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserFrame.setLocationRelativeTo(null);
        UserPanel.setLayout(null);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        Back.setFont(font);
        Change.setFont(font);
        LabelID.setFont(font);
        LabelName.setFont(font);
        LabelSurname.setFont(font);
        LabelYear.setFont(font);
        LabelHouse.setFont(font);
        LabelBoardingStatus.setFont(font);

        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                HouseMasterPage.HMFrame.setVisible(true);
                UserFrame.dispose();
            }
        });

        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                //LoadFile();

                Database.UpdateUser();
                ID.setText(null);
                Name.setText(null);
                Surname.setText(null);
                Year.setText(null);
                House.setText(null);
                BoardingStatus.setText(null);
                JOptionPane.showMessageDialog(UserFrame, "The User Has Been Added");
            }
        });

        POS_ITEM(ID, UserPanel, 400, 50, 150, 30);
        POS_ITEM(Name, UserPanel, 400, 90, 150, 30);
        POS_ITEM(Surname, UserPanel, 400, 130, 150, 30);
        POS_ITEM(Year, UserPanel, 400, 170, 150, 30);
        POS_ITEM(House, UserPanel, 400, 210, 150, 30);
        POS_ITEM(BoardingStatus, UserPanel, 400, 250, 150, 30);
        POS_ITEM(LabelID, UserPanel, 200, 50, 150, 30);
        POS_ITEM(LabelName, UserPanel, 200, 90, 150, 30);
        POS_ITEM(LabelSurname, UserPanel, 200, 130, 150, 30);
        POS_ITEM(LabelYear, UserPanel, 200, 170, 150, 30);
        POS_ITEM(LabelHouse, UserPanel, 200, 210, 150, 30);
        POS_ITEM(LabelBoardingStatus, UserPanel, 200, 250, 150, 30);
        POS_ITEM(Back, UserPanel, 200, 350, 150, 50);
        POS_ITEM(Change, UserPanel, 400, 350, 150, 50);
    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int Width, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, Width, Height);
    }
}
