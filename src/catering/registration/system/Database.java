package catering.registration.system;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Database {

    public static Hashtable Users = new Hashtable(); //Creates a Hash Table called 'Users'
    public static Enumeration names; //Creates an Enumeration called 'names'
    public static ChangeUserPage EditUser = new ChangeUserPage();
    public static String IP = "10.10.17.29";

    public static void main(String[] args) {
    }

    public static void UserAttendance(String SelectedUserID) {



        int Total_Breakfast = 0;
        int Total_Lunch = 0;
        int Total_Dinner = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //Connects to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();
            String SQL = "SELECT Users.ID, Users.Surname, Users.Name, Users.House, Attendance_Date, Attendance.Breakfast, Attendance.Lunch, Attendance.Dinner FROM Users INNER JOIN Attendance ON Users.Barcode=Attendance.User_Id WHERE Users.ID=" + SelectedUserID + " ORDER BY Users.House";
            s.executeQuery(SQL);

            ResultSet rs = s.getResultSet();

            while (rs.next()) {

                String Attendance_Date = rs.getString("Attendance_Date");

                int Breakfast = rs.getInt("Breakfast");
                int Lunch = rs.getInt("Lunch");
                int Dinner = rs.getInt("Dinner");

                if (Breakfast == 1) {
                    String NewRow[] = {Attendance_Date, "", ""};
                    Total_Breakfast++;
                    CateringStaffPage.Model.addRow(NewRow);

                } else if (Lunch == 1) {
                    String NewRow[] = {"", Attendance_Date, ""};
                    Total_Lunch++;
                    CateringStaffPage.Model.addRow(NewRow);

                } else if (Dinner == 1) {
                    String NewRow[] = {"", "", Attendance_Date};
                    Total_Dinner++;
                    CateringStaffPage.Model.addRow(NewRow);
                }
            }
            rs.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server : " + e.toString());
        }

        String B = Integer.toString(Total_Breakfast);
        String L = Integer.toString(Total_Lunch);
        String D = Integer.toString(Total_Dinner);
        String NewRow[] = {B, L, D};

        CateringStaffPage.Total.addRow(NewRow);
    }

    public static void users(String Selected_Date) {

        int Total_Breakfast = 0;
        int Total_Lunch = 0;
        int Total_Dinner = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();
            String SQL = "SELECT Users.Surname, Users.Name, Attendance.Breakfast, Attendance.Lunch, Attendance.Dinner FROM Users INNER JOIN Attendance ON Users.Barcode=Attendance.User_Id WHERE Attendance_Date='" + Selected_Date + "' ORDER BY Users.Surname";
            s.executeQuery(SQL);

            ResultSet rs = s.getResultSet();

            while (rs.next()) {

                String UserName = rs.getString("Surname") + " , " + rs.getString("Name");

                int Breakfast = rs.getInt("Breakfast");
                int Lunch = rs.getInt("Lunch");
                int Dinner = rs.getInt("Dinner");

                if (Breakfast == 1) {
                    String NewRow[] = {UserName, "", ""};
                    Total_Breakfast++;
                    CateringStaffPage.Model.addRow(NewRow);

                } else if (Lunch == 1) {
                    String NewRow[] = {"", UserName, ""};
                    Total_Lunch++;
                    CateringStaffPage.Model.addRow(NewRow);

                } else if (Dinner == 1) {
                    String NewRow[] = {"", "", UserName};
                    Total_Dinner++;
                    CateringStaffPage.Model.addRow(NewRow);
                }
            }
            rs.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server : " + e.toString());
        }

        String B = Integer.toString(Total_Breakfast);
        String L = Integer.toString(Total_Lunch);
        String D = Integer.toString(Total_Dinner);
        String NewRow[] = {B, L, D};

        CateringStaffPage.Total.addRow(NewRow);
    }

    public static void Add() {

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();

            String ID = AddUserPage.ID.getText();
            String Name = AddUserPage.Name.getText();
            String Surname = AddUserPage.Surname.getText();
            String House = AddUserPage.House.getText();
            String Year = AddUserPage.Year.getText();
            String Boarding_Status = AddUserPage.BoardingStatus.getText();

            String SQL = "INSERT INTO Users (Barcode, Name, Surname, Year, House, BoardingStatus) VALUES ('" + ID + "','" + Name + "','" + Surname + "','" + Year + "','" + House + "','" + Boarding_Status + "')";
            s.executeUpdate(SQL);
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server: " + e.toString());
        }
    }
    
    //Used to Add Users to the Database 'Users'. A connection is established with the Database. Various Strings obtained the inputted text and pass them on to be inserted into the database.
    //The statement is then closed, as is the connection. If there is an error, a message is produced with text and the error.

    public static void GetNames(String House) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();
            String SQL = "SELECT * from Users";
            if (House.equalsIgnoreCase("none")) {
                SQL = "SELECT * from Users";
            } else {
                SQL = "SELECT * from Users Where House='" + House + "'";
            }
            s.executeQuery(SQL);
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                String record = rs.getString("Surname") + " , " + rs.getString("Name");
                String record_id = rs.getString("ID");
                int y = Integer.parseInt(record_id);
                Users.put(record, new Integer(y));
            }
            rs.close();
            s.close();
            conn.close();

            names = Users.keys();

            String str;
            DeleteUserPage.List.removeAllItems();
            EditUserPage.List.removeAllItems();
            CateringStaffPage.List.removeAllItems();

            while (names.hasMoreElements()) {
                str = (String) names.nextElement();
                DeleteUserPage.List.addItem(str);
                EditUserPage.List.addItem(str);
                CateringStaffPage.List.addItem(str);
            }

        } catch (Exception e) {
            System.err.println("Cannot connect to database server: " + e.toString());
        }
    }

    public static void DeleteUser(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();
 String SQL = "DELETE FROM Users WHERE ID=" + id;
            s.executeUpdate(SQL);
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server: " + e.toString());
        }
    }

    public static void getRecord(String ID) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * from Users where ID=" + ID);
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
               EditUser.window();
              EditUser.ID.setText(rs.getString("Barcode"));
                EditUser.Name.setText(rs.getString("Name"));
                EditUser.Surname.setText(rs.getString("Surname"));
                EditUser.Year.setText(rs.getString("Year"));
                EditUser.House.setText(rs.getString("House"));
                EditUser.BoardingStatus.setText(rs.getString("BoardingStatus"));
                EditUser.UserID = ID;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server : " + e.toString());
        }
    }

    public static void UpdateUser() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/Imran", "Imran", "Imran");
            Statement s = conn.createStatement();

            String ID = EditUser.ID.getText();
            String Name = EditUser.Name.getText();
            String Surname = EditUser.Surname.getText();
            String House = EditUser.House.getText();
            String Year = EditUser.Year.getText();
            String Boarding_Status = EditUser.BoardingStatus.getText();
            String UserID = EditUser.UserID;

            String SQL = "UPDATE Users SET Barcode='" + ID + "', Name='" + Name + "', Surname='" + Surname + "', Year=" + Year + ", House='" + House + "',BoardingStatus='" + Boarding_Status + "' WHERE ID=" + UserID;

            s.executeUpdate(SQL);
            s.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Updated, YEY !!!");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server : " + e.toString());
        }

    }
}