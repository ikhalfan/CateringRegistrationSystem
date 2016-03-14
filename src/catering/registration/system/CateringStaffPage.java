package catering.registration.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import java.awt.print.*;
import java.text.*;

public class CateringStaffPage extends Database {

    final static String Col[] = {"Breakfast", "Lunch", "Dinner"};
    final static String Rows[][] = {};
    final static String TotCol[] = {"Breakfast", "Lunch", "Dinner"};
    final static String TotRows[][] = {};
    public static DefaultTableModel Model = new DefaultTableModel(Rows, Col);
    public static DefaultTableModel Total = new DefaultTableModel(TotRows, TotCol);
    static JButton Go = new JButton("Go");
    static JButton GoStudent = new JButton("Go");
    static JButton Out = new JButton();
    static JFrame frame = new JFrame("Catering Staff Page");
    static JPanel panel = new JPanel();
    static JLabel T = new JLabel("Totals Of The Day");
    static JLabel S = new JLabel("Enter Date To Sort By");
    static JComboBox Day = new JComboBox();
    static JComboBox Month = new JComboBox();
    static JComboBox Year = new JComboBox();
    static JButton Print = new JButton("Print List");
    static JLabel SortDay = new JLabel("Day");
    static JLabel SortMonth = new JLabel("Month");
    static JLabel SortYear = new JLabel("Year");
    static public String ButtonOut = "";
    static JComboBox List = new JComboBox();
    public static String House;

    public static void main(String[] args) {
        table();
        //  Total();


//SELECT Users.Surname, Users.Name, Attendance.Breakfast, Attendance.Lunch, Attendance.Dinner
//FROM Users
//INNER JOIN Attendance
//ON Users.Barcode=Attendance.User_Id
//WHERE Attendance_Date="29/02/2012"
//ORDER BY Users.Surname
//

    }

    public static void table() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTable table = new JTable(Model);
        final JTable table2 = new JTable(Total);

        JScrollPane scrollPane2 = new JScrollPane(table2);

        table2.setPreferredScrollableViewportSize(new Dimension(200, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        panel.add(scrollPane);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        panel.add(Day);
        panel.add(Month);
        panel.add(Year);
        panel.add(SortDay);
        panel.add(SortMonth);
        panel.add(SortYear);
        panel.add(Go);
        panel.add(GoStudent);
        panel.add(Out);
        panel.add(T);
        panel.add(S);
        panel.add(Print);
        panel.add(scrollPane2);
        panel.add(List);

        Font font = new Font("Marker Felt", Font.BOLD, 15);
        T.setFont(font);
        S.setFont(font);
        SortDay.setFont(font);
        SortMonth.setFont(font);
        SortYear.setFont(font);
        Go.setFont(font);
        Print.setFont(font);
        Out.setFont(font);

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        POS_ITEM(scrollPane, panel, 20, 40, 500, 400);
        POS_ITEM(scrollPane2, panel, 20, 490, 500, 50);
        POS_ITEM(T, panel, 200, 450, 250, 30);
        POS_ITEM(S, panel, 555, 10, 250, 30);
        POS_ITEM(Day, panel, 600, 40, 100, 30);
        POS_ITEM(Month, panel, 600, 80, 100, 30);
        POS_ITEM(Year, panel, 600, 120, 100, 30);
        POS_ITEM(SortDay, panel, 550, 40, 50, 30);
        POS_ITEM(SortMonth, panel, 550, 80, 50, 30);
        POS_ITEM(SortYear, panel, 550, 120, 50, 30);
        POS_ITEM(Go, panel, 600, 160, 100, 30);
        POS_ITEM(Out, panel, 600, 350, 150, 50);
        POS_ITEM(List, panel, 600, 210, 150, 50);
        POS_ITEM(GoStudent, panel, 600, 250, 100, 30);

        POS_ITEM(Print, panel, 550, 280, 240, 50);



        Day.removeAllItems(); //Clears the drop-down box called Day
        for (int Select_Day = 1; Select_Day < 32; Select_Day++) { //Start with 1 and incriment till 31
            String D = Integer.toString(Select_Day); //Convert from an int to a String
            if (D.length() == 1) { //If the day is 1 int long
                Day.addItem("0" + D); //Add a 0 before it
            } else {
                Day.addItem(D); //If not, just add the day
            }

        }
        Month.removeAllItems();
        for (int Select_Month = 1; Select_Month < 13; Select_Month++) {
            String M = Integer.toString(Select_Month);
            if (M.length() == 1) {
                Month.addItem("0" + M);
            } else {
                Month.addItem(M);
            }


        }
        Year.removeAllItems();
        for (int Select_Year = 2012; Select_Year < 2020; Select_Year++) {
            Year.addItem(Integer.toString(Select_Year));
        }

        Go.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                while (Total.getRowCount() > 0) {
                    Total.removeRow(0);
                }

                while (Model.getRowCount() > 0) {
                    Model.removeRow(0);
                }

                String Selected_Date = Day.getSelectedItem().toString() + "/" + Month.getSelectedItem().toString() + "/" + Year.getSelectedItem().toString();
                Database.users(Selected_Date);
            }
        });

        Print.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    String Selected_Date = Day.getSelectedItem().toString() + "/" + Month.getSelectedItem().toString() + "/" + Year.getSelectedItem().toString();
                    MessageFormat headerFormat = new MessageFormat(Selected_Date);
                    MessageFormat footerFormat = new MessageFormat(" Page {0} ");
                    table.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                    table2.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);

                } catch (PrinterException pe) {
                    System.err.println("Error printing: " + pe.getMessage());
                }

            }
        });

        Out.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (ButtonOut.equalsIgnoreCase("Catering")) {
                    CateringRegistrationSystem.LoginFrame.setVisible(true);
                    frame.dispose();
                } else {
                    HouseMasterPage.HMFrame.setVisible(true);
                    frame.dispose();
                }
            }
        });

        GoStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                while (Total.getRowCount() > 0) {
                    Total.removeRow(0);
                }

                while (Model.getRowCount() > 0) {
                    Model.removeRow(0);
                }

                String SelectedName = List.getSelectedItem().toString();

                if (Users.containsKey(SelectedName)) {
                    String id = Users.get(SelectedName).toString();
                    UserAttendance(id);

                } else {
                    JOptionPane.showMessageDialog(frame, "User Not Found");
                }
            }
        });

        if (ButtonOut.equalsIgnoreCase("Catering")) {
            Out.setText("Log Out");
            GoStudent.setVisible(false);
        } else {
            Out.setText("Back");
            GoStudent.setVisible(true);

        }

        Out.repaint();
        frame.repaint();

        //THis is for selecting the student, catering do not see this.
        if (CateringRegistrationSystem.CateringStaffUser == true) {
            //Database.GetNames("none"); 
            List.setVisible(false);

        } else {
            List.setVisible(true);

            Database.GetNames(House);
        }
    }

    public static void POS_ITEM(Component Component, JPanel Panel, int X, int Y, int Width, int Height) {
        Insets insets = Panel.getInsets();
        Dimension size = Component.getPreferredSize();
        Component.setBounds(insets.left + X, insets.top + Y, Width, Height);
    }
}