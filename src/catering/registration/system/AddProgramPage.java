package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;

public class AddProgramPage {

    static JFrame ProgramFrame = new JFrame("Add User Page");
    static JPanel ProgramPanel = new JPanel();
    static JButton Back = new JButton("Back");
    static JButton Add = new JButton("Add");
    static JTextField Username = new JTextField();
    static JTextField Password = new JTextField();
    static JComboBox Department = new JComboBox();
    static JComboBox House = new JComboBox();
    static JLabel LabelUsername = new JLabel("Username");
    static JLabel LabelPassword = new JLabel("Password");
    static JLabel LabelDepartment = new JLabel("Department");
    static JLabel LabelHouse = new JLabel("House");

    public void main() {

        ProgramFrame.add(ProgramPanel); //Add the Panel to the Frame
        ProgramPanel.add(Back); //Add the Back Button to the Panel
        ProgramPanel.add(Add);
        ProgramPanel.add(Username);
        ProgramPanel.add(Password);
        ProgramPanel.add(Department);
        ProgramPanel.add(House);
        ProgramPanel.add(LabelUsername); //Add the Username Label to the Panel
        ProgramPanel.add(LabelPassword);
        ProgramPanel.add(LabelDepartment);
        ProgramPanel.add(LabelHouse);
        ProgramFrame.setSize(800, 600); //Set the size of the Frame
        ProgramFrame.setVisible(true); //Set the Frame to being visible
        ProgramPanel.setLayout(null);
        ProgramFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the program to quit when the Frame is closed
        ProgramFrame.setLocationRelativeTo(null);

        Font font = new Font("Marker Felt", Font.BOLD, 15); //Create a Font called font of size 15, Bold and Marker Felt
        Back.setFont(font); //Set the Button to that font
        Add.setFont(font);
        LabelUsername.setFont(font); //Set the label to that font.
        LabelPassword.setFont(font);
        LabelUsername.setFont(font);
        LabelPassword.setFont(font);
        LabelHouse.setFont(font);
        LabelDepartment.setFont(font);

        Back.addActionListener(new ActionListener() { //Set an ActionListener to the Back Button

            public void actionPerformed(ActionEvent evt) { //If the action is performed...
                HouseMasterPage.HMFrame.setVisible(true); //Show the HouseMasterPage
                ProgramFrame.dispose(); //Dispose of this frame, ProgramFrame
            }
        });

        Add.addActionListener(new ActionListener() { //Set an ActionListener to the Add Button

            public void actionPerformed(ActionEvent evt) { //If the action is performed...

                String NewUser = Username.getText() + "," + Password.getText() + "," + Department.getSelectedItem().toString() + "," + House.getSelectedItem().toString();
                SaveFile(NewUser); 
                //Create a String called NewUser that gets the text entered in Username, Password, Department and House. Seperate the data on a single line with commas (,) as the delimiters.
                //Pass the String over to the SaveFile method
                
                JOptionPane.showMessageDialog(ProgramFrame, "The User Has Been Added"); //Display a message that the User has been added.
            }
        });

        Department.removeAllItems(); //Clear the Department Combo-Box
        Department.addItem("House Master"); //Add the text 'House Master' to the Combo-Box
        Department.addItem("Catering"); //Add the text 'Catering' to the Combo-Box
        Department.addItem("Admin"); //Add the text 'Admin' to the Combo-Box
        House.removeAllItems(); //Clear the House Combo-Box
        House.addItem("None"); //Add the House 'none'
        House.addItem("Gepps");
        House.addItem("Stockss");
        House.addItem("Windsors");
        House.addItem("Monts");
        House.addItem("Elywns");
        House.addItem("Follyfield");
        House.addItem("Deacons");
        House.addItem("Manor");
        House.addItem("Garnetts");
        House.addItem("Thorne");

        POS_ITEM(Username, ProgramPanel, 400, 50, 150, 30); //Position the textfield 'Username'
        POS_ITEM(Password, ProgramPanel, 400, 90, 150, 30);
        POS_ITEM(Department, ProgramPanel, 400, 130, 150, 30);
        POS_ITEM(House, ProgramPanel, 400, 170, 150, 30);
        POS_ITEM(LabelUsername, ProgramPanel, 200, 50, 150, 30);
        POS_ITEM(LabelPassword, ProgramPanel, 200, 90, 150, 30);
        POS_ITEM(LabelDepartment, ProgramPanel, 200, 130, 150, 30);
        POS_ITEM(LabelHouse, ProgramPanel, 200, 170, 150, 30);
        POS_ITEM(Back, ProgramPanel, 200, 350, 150, 50);
        POS_ITEM(Add, ProgramPanel, 400, 350, 150, 50);

    }
    public static String SaveFile = "jkl/Users/imz_k/Desktop/Login.txt"; // String with the Login file name in it.

    private static void SaveFile(String Message) { //Method called SaveFile with Parameter Message - Message is populated with data from NewUser
        try {
            String MyMessage = Message + "\n"; //Passes Message + a new line to MyMessage
            RandomAccessFile file = new RandomAccessFile(SaveFile, "rw"); //Allows reading and writing to the TextFile
            file.seek(file.length()); //Sets the pointer to the end of the file
            file.write(MyMessage.getBytes()); //Get the inputted information
            file.close(); //Closes the file
        } catch (IOException error) { //In case of an error...
            JOptionPane.showMessageDialog(null, "Problem - Could not save the file.." + error.toString()); //Display an error message + the error
        }
    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int WUserPasswordth, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, WUserPasswordth, Height);
    }
}