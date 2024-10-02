
package testapp2a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class TESTAPP2A{
    
 
public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:app.db"); 
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID: ");
        int id = sc.nextInt();
        System.out.println("Student first name:");
        String fname = sc.next();
        System.out.println("Student last name:");
        String lname = sc.next();
        System.out.println("Student email name:");
        String email = sc.next();
        System.out.println("Student status name:");
        String status = sc.next();
        
        String sql = "INSERT INTO Student (s_id, s_fname, s_lastname, s_email, s_status) VALUES(?, ?, ?, ?, ?)";
          try{ 
          Connection con = connectDB();
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, id);
          pst.setString(2, fname);
          pst.setString(3, lname);
          pst.setString(4, email);
          pst.setString(5, status);
          pst.executeUpdate();
              System.out.println("Inserted Successfully!");
          } catch (SQLException e){
              System.out.println("Connection error"+e.getMessage());
          
          }
    }
    
}