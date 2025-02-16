import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataGovernanceSystem {
    private Map<String, Employee> employees;
    private Map<String, String> users;
    private List<String> logs;

    private static class Employee {
        String name;
        String email;
        String department;

        Employee(String name, String email, String department) {
            this.name = name;
            this.email = email;
            this.department = department;
        }
    }

    public DataGovernanceSystem() {
        employees = new HashMap<>();
        users = new HashMap<>();
        logs = new ArrayList<>();
    }

    private void addEmployee(String name, String email, String department, String role) {
        employees.put(name, new Employee(name, email, department));
        logs.add("[" + role + "] Added employee: " + name + " in department: " + department);
    }

    private void viewEmployees() {
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            Employee emp = entry.getValue();
            System.out.println("Name: " + emp.name
                    + ", Email: " + emp.email
                    + ", Department: " + emp.department);
        }
    }

    private void updateEmployee(String name, String email, String department, String role) {
        if (employees.containsKey(name)) {
            employees.put(name, new Employee(name, email, department));
            logs.add("[" + role + "] Updated employee: " + name);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void deleteEmployee(String name, String role) {
        if (employees.containsKey(name)) {
            employees.remove(name);
            logs.add("[" + role + "] Deleted employee: " + name);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void viewLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataGovernanceSystem system = new DataGovernanceSystem();

        mainMenu:
        while (true) {
            System.out.println("\n=================== Welcome to Accenture! ===================\n");
            System.out.println("=============================================================\n");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            String startChoice = scanner.nextLine();

            if (startChoice.equals("2")) {
                System.out.println("Exiting...");
                break; 
            } else if (!startChoice.equals("1")) {
                System.out.println("Invalid choice.");
                continue; 
            }

            System.out.print("\nPlease enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Choose role (admin/manager/employee): ");
            String role = scanner.nextLine();

            while (true) {
                System.out.println("\n=============================================================\n");
                System.out.println("===================== Available commands ====================\n");
                if (role.equalsIgnoreCase("admin")) {
                    System.out.println("1. Add Employee");
                    System.out.println("2. View Employees");
                    System.out.println("3. Update Employee");
                    System.out.println("4. Delete Employee");
                    System.out.println("5. View Logs");
                    System.out.println("6. Exit (Log Out)");
                } else if (role.equalsIgnoreCase("manager")) {
                    System.out.println("1. View Employees");
                    System.out.println("2. Update Employee");
                    System.out.println("3. Exit (Log Out)");
                } else if (role.equalsIgnoreCase("employee")) {
                    System.out.println("1. View Employees");
                    System.out.println("2. Exit (Log Out)");
                } else {
                    System.out.println("Invalid role.");
                    break; 
                }

                System.out.print("\nEnter your choice: ");
                String choice = scanner.nextLine();

                switch (role.toLowerCase()) {
                    case "admin":
                        handleAdminChoice(system, scanner, choice, role);
                        if (choice.equals("6")) {
                            System.out.println("Logging out " + username + " (admin)...");
                            break;
                        }
                        break;
                    case "manager":
                        handleManagerChoice(system, scanner, choice, role);
                        if (choice.equals("3")) {
                            System.out.println("Logging out " + username + " (manager)...");
                            break;
                        }
                        break;
                    case "employee":
                        handleEmployeeChoice(system, scanner, choice);
                        if (choice.equals("2")) {
                            System.out.println("Logging out " + username + " (employee)...");
                            break;
                        }
                        break;
                    default:
                        System.out.println("Invalid role.");
                        break;
                }

                if ((role.equalsIgnoreCase("admin") && choice.equals("6"))
                        || (role.equalsIgnoreCase("manager") && choice.equals("3"))
                        || (role.equalsIgnoreCase("employee") && choice.equals("2"))) {
                    break;
                }
            }
        }
        scanner.close();
    }

    private static void handleAdminChoice(
            DataGovernanceSystem system, Scanner scanner,
            String choice, String role) {
        switch (choice) {
            case "1":
                System.out.print("\nEnter employee name: ");
                String name = scanner.nextLine();
                System.out.print("Enter employee email: ");
                String email = scanner.nextLine();
                System.out.print("Enter department (IT/Compliance/Legal/Finance/Operation): ");
                String department = scanner.nextLine();
                system.addEmployee(name, email, department, role);
                break;
            case "2":
                system.viewEmployees();
                break;
            case "3":
                System.out.print("\nEnter employee name: ");
                name = scanner.nextLine();
                System.out.print("Enter new email: ");
                email = scanner.nextLine();
                System.out.print("Enter new department: ");
                department = scanner.nextLine();
                system.updateEmployee(name, email, department, role);
                break;
            case "4":
                System.out.print("Enter employee name: ");
                name = scanner.nextLine();
                system.deleteEmployee(name, role);
                break;
            case "5":
                system.viewLogs();
                break;
            case "6":
  
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void handleManagerChoice(
            DataGovernanceSystem system, Scanner scanner,
            String choice, String role) {
        switch (choice) {
            case "1":
                system.viewEmployees();
                break;
            case "2":
                System.out.print("Enter employee name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new email: ");
                String email = scanner.nextLine();
                System.out.print("Enter new department: ");
                String department = scanner.nextLine();
                system.updateEmployee(name, email, department, role);
                break;
            case "3":
       
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void handleEmployeeChoice(DataGovernanceSystem system, Scanner scanner, String choice) {
        switch (choice) {
            case "1":
                system.viewEmployees();
                break;
            case "2":
             
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
}