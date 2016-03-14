package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.*;

public class DeleteProgramPage {

    static JFrame ProgramFrame = new JFrame("Delete User Page");
    static JPanel ProgramPanel = new JPanel();
    static JButton Back = new JButton("Back");
    static JButton Delete = new JButton("Delete");
    static JComboBox ProgramUserList = new JComboBox();
    static JLabel LabelProgramUserList = new JLabel("Select User To Delete");
    static Stack<String> sk = new Stack<String>();
    static Stack<String> sk2 = new Stack<String>();

    public void main() {

        ProgramFrame.add(ProgramPanel);
        ProgramPanel.add(Back);
        ProgramPanel.add(Delete);
        ProgramPanel.add(ProgramUserList);
        ProgramPanel.add(LabelProgramUserList);
        ProgramFrame.setSize(800, 600);
        ProgramFrame.setVisible(true);
        ProgramPanel.setLayout(null);
        ProgramFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProgramFrame.setLocationRelativeTo(null);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        Back.setFont(font);
        Delete.setFont(font);
        LabelProgramUserList.setFont(font);

        Back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //LoadFile();
                HouseMasterPage.HMFrame.setVisible(true);
                ProgramFrame.dispose();
            }
        });

        Delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                String Delete = ProgramUserList.getSelectedItem().toString(); //This will be from the Combo-Box

                while (sk.isEmpty() == false) {
                    String k = sk.peek();

                    String UserName[] = k.split(",");
                    k = k.replace("|", "");

                    if (Delete.equalsIgnoreCase(UserName[0])) {
                        sk.pop();
                    } else {
                        sk.pop();
                        sk2.push(k);
                    }
                }
                SaveFile(); //then sk2 pops to the save file...

                JOptionPane.showMessageDialog(ProgramFrame, "The User Has Been Deleted");
            }
        });

        POS_ITEM(ProgramUserList, ProgramPanel, 400, 50, 150, 30);
        POS_ITEM(LabelProgramUserList, ProgramPanel, 200, 50, 150, 30);
        POS_ITEM(Back, ProgramPanel, 200, 350, 150, 50);
        POS_ITEM(Delete, ProgramPanel, 400, 350, 150, 50);

        Users_Read_File();
    }
    public static String SaveFile = "/Users/imz_k/Desktop/Login.txt"; // String with the file name in

    private static void SaveFile() {
        try {
            String MyMessage = "";
            RandomAccessFile file = new RandomAccessFile(SaveFile, "rw");
            file.setLength(0);
            while (sk2.isEmpty() == false) {
                MyMessage = "";
                MyMessage = sk2.pop() + "\n";
                file.write(MyMessage.getBytes());
            }
            file.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Problem - Could not save the file.." + error.toString());
        }
    }

    public static void Users_Read_File() {

        sk.empty();
        sk2.empty();

        try {
            ProgramUserList.removeAllItems();

            RandomAccessFile file = new RandomAccessFile(SaveFile, "r");
            file.seek(0);
            String line = file.readLine();
            while (line != null) {
                sk.push(line + "|");
                String FileLine[] = line.split(",");

                if (FileLine[0].equalsIgnoreCase("null")) {
                } else {
                    ProgramUserList.addItem(FileLine[0]);
                }
                line = file.readLine();
            }

            file.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Problem - Could not load the file.." + error.toString());
        }
    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int WUserPasswordth, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, WUserPasswordth, Height);
    }
}