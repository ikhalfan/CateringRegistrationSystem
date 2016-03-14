package catering.registration.system;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class CateringRegistrationSystem {

    static JFrame LoginFrame = new JFrame("Login");
    static JButton LoginButton = new JButton("Login");
    static JTextField Username = new JTextField("Username");
    static JPasswordField Password = new JPasswordField("Password");
    static boolean Eraseusername = false;
    static boolean Erasepassword = false;
    static public Image background = null;
    static boolean CateringStaffUser = false;
    static JPanel LoginPanel = new javax.swing.JPanel() {

        @Override
        public void paintComponent(Graphics g) {
            try {
                background = ImageIO.read(new File("/Volumes/ikhalfan/IB_Java/Imran Khalfan/School Logo.png"));
            } catch (Exception ex) {
            }
            g.drawImage(background, 300, 0, this);
        }
    };

    public static void main(String[] args) {

        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginFrame.add(LoginPanel);
        LoginPanel.add(LoginButton);
        LoginFrame.setSize(800, 600);
        LoginPanel.add(Username);
        LoginPanel.add(Password);
        LoginFrame.setBackground(Color.white);
        LoginFrame.setLocationRelativeTo(null);
        LoginPanel.setLayout(null);

        ObjectLocation(Username, 300, 200);
        ObjectLocation(Password, 300, 250);
        ObjectLocation(LoginButton, 300, 350);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        LoginButton.setFont(font);
        
        LoginButton.setSize(150, 30);
        Username.setSize(180, 30);
        Password.setSize(180, 30);

        LoginFrame.setVisible(true);


        Password.addKeyListener((new KeyAdapter() { //Adds a KeyListener to the Password text-file

            @Override
            public void keyPressed(KeyEvent e) { //Create an event called e
                int key = e.getKeyCode(); //If e happens, get the characters inputted
                if (key == KeyEvent.VK_ENTER) { //If Enter is pressed
                    LoadFile(); //Load LoadFile
                }
            }
        }));

        Username.setForeground(Color.gray); //Set the forground to the colour gray
        Username.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent anything) {

                if (Eraseusername == false) { //If EraseUserName(boolean) is compared to false
                    Username.setText(""); //Set the TextField to a blank text
                    Eraseusername = true; //Set the Boolean to true
                }
                Username.setForeground(Color.black); //Set the Forground colour to Black
                Username.setBackground(Color.white); //Set the Background colour to White
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        Password.setForeground(Color.gray);
        Password.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent anything) {

                if (Erasepassword == false) {
                    Password.setText("");
                    Erasepassword = true;
                }
                Password.setForeground(Color.black);
                Password.setBackground(Color.white);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        LoginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                LoadFile();
            }
        });

        LoginFrame.requestFocusInWindow(); //Sets the focus on the Frame.

    }

    public static void ObjectLocation(JButton LoginButton, int left, int top) {
        Insets insets = LoginButton.getInsets();
        Dimension size = LoginButton.getPreferredSize();
        LoginButton.setBounds(left + insets.left, top + insets.top, size.width, size.height);
    }

    public static void ObjectLocation(JTextField Username, int left, int top) {
        Insets insets = Username.getInsets();
        Dimension size = Username.getPreferredSize();
        Username.setBounds(left + insets.left, top + insets.top, size.width, size.height);
    }

    public static void ObjectLocation(JPasswordField Password, int left, int top) {
        Insets insets = Password.getInsets();
        Dimension size = Password.getPreferredSize();
        Password.setBounds(left + insets.left, top + insets.top, size.width, size.height);
    }
    public static String PasswordFile = "/Users/imz_k/Desktop/Login.txt"; //Location of the Login file (Called PasswordFile)

    private static void LoadFile() {
        String SelectedHouse = ""; //Creates an empty String called SelectedHouse
        String SelectedWindow = ""; //Creates an empty String called SelectedWindow
        try {
            Boolean pass = false; //If the boolean called pass is false

            RandomAccessFile file = new RandomAccessFile(PasswordFile, "r"); //Read the file 'PasswordFile'
            file.seek(0); //Set the pointer to the start

            String line = file.readLine(); //Creat a String called line that reads the lines
            while (line != null) { //While there is still a line to read
                if (line != null) { //If the line to read is not empty
                    String data[] = line.split(","); //Split the line by commas (,) into elements
                    if (Username.getText().equalsIgnoreCase(data[0])) { //The first element is the Username
                        if (Password.getText().equalsIgnoreCase(data[1])) { //The second element is the Password
                            //then login as correct
                            pass = true;
                            SelectedWindow = data[2]; //The third element is the Window and is passed to SelectedWindow
                            SelectedHouse = data[3];
                        }
                    }
                }
                line = file.readLine();
            }
            file.close();

            if (pass == true) { //if the boolean pass is true
                if (SelectedWindow.equalsIgnoreCase("HM")) { //If SelectedWindow is HM
                    //load HM
                    HouseMasterPage NextWindow = new HouseMasterPage(); //Create a new instrace of the HouseMasterPage called NextWindow
                    NextWindow.House = SelectedHouse; //Call the House method
                    NextWindow.main(); //Load the main method
                    CateringStaffUser = false; //Set CateringStaffUser to false
                    LoginFrame.setVisible(false); //Hide this Frame

                } else if (SelectedWindow.equalsIgnoreCase("Admin")) {
                    //load Admin
                    HouseMasterPage NextWindow = new HouseMasterPage();
                    NextWindow.House = "admin";
                    // NextWindow.Admin = true;
                    NextWindow.main();
                    CateringStaffUser = false;
                    LoginFrame.setVisible(false);

                } else {
                    String Args[] = new String[0];
                    CateringStaffPage.main(Args);
                    CateringStaffPage.ButtonOut = "Catering";
                    CateringStaffUser = true;
                    LoginFrame.setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(LoginFrame, "The Username And Password Do Not Match"); //Display an error Message if the Username and Passwords do not match
            }

        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Problem - Could not load the file.." + error.toString()); //Display and error message if the file can't be found
        }

    }

    private static void SaveFile(String Message) {
        try {
            String GUITextArea = Message + "/r";
            RandomAccessFile file = new RandomAccessFile(PasswordFile, "rw");
            file.seek(file.length());
            file.write(GUITextArea.getBytes());
            file.close();

        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Problem - Could not save the file.." + error.toString());
        }
    }
}