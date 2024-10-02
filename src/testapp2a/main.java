/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp2a;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        TestAppV2 app = new TestAppV2();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to the Patient Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    app.addStudents();
                    break;
                case 2:
                    app.viewStudents();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return; // Exit the loop and terminate the program
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}