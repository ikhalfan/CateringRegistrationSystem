package LogStudent;

import java.util.Scanner; //Import the scanner library (For Input)
import java.util.Date; //Import the date library
import java.text.DateFormat; //Imports the date format library
import java.text.SimpleDateFormat;
import java.sql.*; //Imports Java MySQL library
import java.awt.*;
import sun.audio.*;    //import the sun.audio package
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Loop();    //Recursion Method as the code loops round...
    }
    public static String IP = "10.10.17.29"; //Sets the IP Address for MySQL
    static Scanner scanner = new Scanner(System.in); //Creates a Scanner called 'scanner' for Input

    public static void Loop() {

        String FingerInput = ""; //Creates an empty string called FingerInput

        try {
            FingerInput = scanner.nextLine(); //Get input from the barcode/Finger scanner
            if (FingerInput.equals("x")) { //Clause to quit the recurssion
                scanner.close(); //Closes the scanner input
                System.exit(0); //Quits the program
            } else {

                //If not, get the current date and time

                DateFormat timeFormat = new SimpleDateFormat("HH"); //Sets format for getting the current Hour
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Sets the format for getting the Date
                Date date = new Date(); //Gets the current Date and Time
                String CurrentDate = dateFormat.format(date).toString(); //Sets a String with the Current Date, based on the format of dateFormat
                int CurrentHour = Integer.parseInt(timeFormat.format(date)); //Sets an integer called CurrentHour in the format of timeFormat


                String SQL = ""; //Creates an empty String called SQL

                if (CurrentHour >= 7 && CurrentHour <= 9) { //If the Hour is equal or greater than 7am and equal to or less that 9am
                    //Breakfast
                    SQL = "INSERT INTO Attendance (User_ID, Attendance_Date, Breakfast, Lunch, Dinner) VALUES" //Sets the SQL for inseting into the Attendance Database
                            + "('" + FingerInput + "','" + CurrentDate + "',1,0,0)"; //Adds the FingerInput, Current Date and 1 into Breakfast and 0 into Lunch and Dinner
                    AddToDatabase(SQL); //Calls the AddToDatabase Method and passes the data into the String SQL
                } else if (CurrentHour >= 12 && CurrentHour <= 14) { //If the Hour is equal or greater than 12pm and equal to or less that 2pm
                    //Lunch
                    SQL = "INSERT INTO Attendance (User_ID, Attendance_Date, Breakfast, Lunch, Dinner) VALUES" //Sets the SQL for inseting into the Attendance Database
                            + "('" + FingerInput + "','" + CurrentDate + "',0,1,0)"; //Adds the FingerInput, Current Date and 1 into Lunch and 0 into Breakfast and Dinner
                    AddToDatabase(SQL); //Calls the AddToDatabase Method and passes the data into the String SQL
                } else if (CurrentHour >= 17 && CurrentHour <= 19) { //If the Hour is equal or greater than 5pm and equal to or less that 7pm
                    //Dinner
                    SQL = "INSERT INTO Attendance (User_ID, Attendance_Date, Breakfast, Lunch, Dinner) VALUES" //Sets the SQL for inseting into the Attendance Database
                            + "('" + FingerInput + "','" + CurrentDate + "',0,0,1)"; //Adds the FingerInput, Current Date and 1 into Dinner and 0 into Breakfast and Lunch
                    AddToDatabase(SQL); //Calls the AddToDatabase Method and passes the data into the String SQL
                } else {
                    //BEEP
                }
                //beep or play sound

                Toolkit.getDefaultToolkit().beep(); //Gets the Beep

                try {
                    InputStream in = new FileInputStream("/Users/imz_k/NetBeansProjects/Catering Registration System 3/beep-8.au"); //Opens the input stream of the Beep file
                    AudioStream as = new AudioStream(in);//Create an AudioStream object from the input stream.
                    AudioPlayer.player.start(as);// Use the static class member "player" from class AudioPlayer to play the clip

                } catch (Exception error) {
                    System.out.println(error); //Print out an error if the Audio File can not be found
                }

                Loop(); //Recurssion - Calls itself to loop round again
            }
        } catch (Exception error) {
            System.out.println(error); //If there is a problem with the Scanner input, it prints out the error

        }
    }

    public static void AddToDatabase(String SQL) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();
            System.out.println();
            s.execute(SQL);
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server: " + e.toString());
        }
    }
}
