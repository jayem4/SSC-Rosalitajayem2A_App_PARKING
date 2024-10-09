package testapp2a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import testappv2a.config;

public class TestAppV2A {
    
   
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String resp;
        do{

            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            TestAppV2A test = new TestAppV2A();
            switch(action){
                case 1:
                    test.addEmployee();
                break;
                case 2:
                    test.viewEmployee();
                break;
                case 3:
                    test.viewEmployee();
                    test.updateEmployee();
                break;
                case 4:
                    test.viewEmployee();
                    test.deleteEmployee();
                    test.viewEmployee();
                break;
            }
            
            System.out.print("Continue? ");
            resp = sc.next();

        }while(resp.equalsIgnoreCase("yes"));
            System.out.println("Thank You!");

    }
    
    public void addEmployee(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Employee First Name: ");
        String fname = sc.nextLine();
        System.out.print("Employee Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Employee Email: ");
        String email = sc.nextLine();
        System.out.print("Employee Status: ");
        String status = sc.nextLine();

        String sql = "INSERT INTO tbl_employees (e_fname, e_lname, e_email, e_status) VALUES (?, ?, ?, ?)";


        conf.addRecord(sql, fname, lname, email, status);
    }
    
    private void viewEmployee() {
        
        String qry = "SELECT * FROM tbl_employees";
        String[] hdrs = {"ID", "First Name", "Last Name", "Email", "Status"};
        String[] clms = {"e_id", "e_fname", "e_lname", "e_email", "e_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    private void updateEmployee(){
    
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the ID to Update: ");
        int id = sc.nextInt();
        
        System.out.print("Enter new First Name: ");
        String nfname = sc.next();
        System.out.print("Enter new Last Name: ");
        String nlname = sc.next();
        System.out.print("Enter new Email: ");
        String nemail = sc.next();
        System.out.print("Enter new Status: ");
        String nstatus = sc.next();
        
        String qry = "UPDATE tbl_employees SET e_fname = ?, e_lname = ?, e_email = ?, e_status = ? WHERE e_id = ?";
        
        config conf = new config();
        conf.updateEmployee(qry, nfname, nlname, nemail, nstatus, id);
        
    }
    
    private void deleteEmployee(){
        
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();
        
        String qry = "DELETE FROM tbl_employees WHERE e_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);
    
    }
    
    
}



    
    //---------------------------------------------------------------------------------------------------------------
    //VIEW METHOD
    //---------------------------------------------------------------------------------------------------------------
    
     
    public void viewRecords(String sqlQuery, String[] columnHeaders, String[] columnNames) {
        // Check that columnHeaders and columnNames arrays are the same length
        if (columnHeaders.length != columnNames.length) {
            System.out.println("Error: Mismatch between column headers and column names.");
            return;
        }

        try (Connection conn = config.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            // Print the headers dynamically
            StringBuilder headerLine = new StringBuilder();
            headerLine.append("--------------------------------------------------------------------------------------------------------------------\n| ");
            for (String header : columnHeaders) {
                headerLine.append(String.format("%-20s | ", header)); // Adjust formatting as needed
            }
            headerLine.append("\n-------------------------------------------------------------------------------------------------------------------");

            System.out.println(headerLine.toString());

            // Print the rows dynamically based on the provided column names
            while (rs.next()) {
                StringBuilder row = new StringBuilder("| ");
                for (String colName : columnNames) {
                    String value = rs.getString(colName);
                    row.append(String.format("%-20s | ", value != null ? value : "")); // Adjust formatting
                }
                System.out.println(row.toString());
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
    }
    
    
    //-----------------------------------------------
    // UPDATE METHOD
    //-----------------------------------------------

    public void updateRecord(String sql, Object... values) {
        try (Connection conn = this.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop through the values and set them in the prepared statement dynamically
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
                } else {
                    pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }
    
    
    //-----------------------------------------------
    // DELETE METHOD
    //-----------------------------------------------

    // Add this method in the config class
public void deleteRecord(String sql, Object... values) {
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Loop through the values and set them in the prepared statement dynamically
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
            } else {
                pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
            }
        }

        pstmt.executeUpdate();
        System.out.println("Record deleted successfully!");
    } catch (SQLException e) {
        System.out.println("Error deleting record: " + e.getMessage());
    }
}
    
    
}
