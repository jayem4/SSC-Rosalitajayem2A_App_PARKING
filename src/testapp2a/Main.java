
package testapp2a;


package testappv2a;

import java.util.Scanner;

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
    