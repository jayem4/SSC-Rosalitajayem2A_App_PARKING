/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp2a;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class TestAppV2 {

 


    public void addStudents(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Patient First Name: ");
        String fname = sc.next();
        System.out.print("Patient Last Name: ");
        String lname = sc.next();
        System.out.print("Patient Age: ");
        String age = sc.next();
        System.out.print("Patient Gender: ");
        String gender = sc.next();
        
        String sql = "INSERT INTO Student (s_fname, s_lname, s_email, s_status) VALUES (?, ?, ?, ?)";

        
        conf.addRecord(sql, fname, lname, age, gender);
        
    
    }

    public void viewStudents() {
        String sql = "SELECT * FROM Student";
        config conf = new config();
        try (Connection conn = conf.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-12s | %-25s | %-10s |\n", "ID", "Firstname", "LastNAme", "Age", "Gender");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("s_id");
                String fname = rs.getString("s_fname");
                String lname = rs.getString("s_lname");
                String age = rs.getString("s_age");
                String gender = rs.getString("s_gender");
                System.out.printf("| %-5d | %-20s | %-12s | %-25s | %-10s |\n", id, fname, lname, age, gender);
            }

            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Error retrieving citizens: " + e.getMessage());
        }
    }



}
