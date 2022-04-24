package entity.human.resources.input;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;

import java.util.Scanner;

public class HumanResources implements Operations {
    Scanner scanner = new Scanner(System.in);
    public int option = 0;

    @Override
    public void displayEntities() {
        System.out.println("Choose an entity to perform an operation");

        System.out.println("1 - Department");
        System.out.println("2 - Employees");
        System.out.println("3 - Projects");

        option = scanner.nextInt();

        switch (option) {
            case 1:
                displayDepartmentOperations();
                break;
            case 2:
                displayEmployeeOperations();
                break;
            case 3:
                displayProjectOperations();
                break;
            default:
                System.out.println("The value for the entity you have provided does not exist");

        }
    }

    @Override
    public void displayDepartmentOperations() {
        DepartmentDao departmentDao = new DepartmentDao();

        System.out.println("Choose one of the following operations");

        System.out.println("1 - Create Department");
        System.out.println("2 - Display Departments");
        System.out.println("3 - Update Department");
        System.out.println("4 - Delete Department");

        System.out.println("Press 0 to terminate program");

        option = scanner.nextInt();
        switch (option) {
            case 1:
                departmentDao.createDepartment();
                break;
            case 2:
                departmentDao.displayDepartments();
                break;
            case 3:
                departmentDao.updateDepartment();
                break;
            case 4:
                departmentDao.deleteDepartment();
                break;
            default:
                if (option == 0) {
                    System.out.println("Leaving application ...");
                } else {
                    System.out.println("Invalid operation");
                }
        }

    }

    @Override
    public void displayEmployeeOperations() {
        EmployeeDao employeeDao = new EmployeeDao();
        System.out.println("Choose one of the following operations");

        System.out.println("1 - Create Employee");
        System.out.println("2 - Display Employees");
        System.out.println("3 - Update Employee");
        System.out.println("4 - Delete Employee");

        System.out.println("Press 0 to terminate program");

        option = scanner.nextInt();
        switch (option) {
            case 1:
                employeeDao.createEmployee();
                break;
            case 2:
                employeeDao.displayEmployees();
                break;
            case 3:
                employeeDao.updateEmployee();
                break;
            case 4:
                employeeDao.deleteEmployee();
                break;
            default:
                if (option == 0) {
                    System.out.println("Leaving application ...");
                } else {
                    System.out.println("Invalid operation");
                }
        }
    }

    @Override
    public void displayProjectOperations() {
        ProjectDao projectDao = new ProjectDao();

        System.out.println("Choose one of the following operations");

        System.out.println("1 - Create Project");
        System.out.println("2 - Display Projects");
        System.out.println("3 - Update Project");
        System.out.println("4 - Delete Project");

        System.out.println("Press 0 to terminate program");

        option = scanner.nextInt();

        switch (option) {
            case 1:
                projectDao.createProject();
                break;
            case 2:
                projectDao.displayProjects();
                break;
            case 3:
                projectDao.updateProject();
                break;
            case 4:
                projectDao.deleteProject();
                break;
            default:
                if (option == 0) {
                    System.out.println("Leaving application ...");
                } else {
                    System.out.println("Invalid operation");
                }

        }
    }
}
