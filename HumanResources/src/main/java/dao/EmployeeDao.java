package dao;

import dao.implementation.EmployeeImplementation;
import entity.Department;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EmployeeDao implements EmployeeImplementation {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void createEmployee() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Employee employee = new Employee();
            System.out.println("Enter employee's information:");
            buildEmployee(employee);
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void displayEmployees() {
        List<Employee> employeeList;
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            String SELECT = "FROM Employee";
            Query selectQuery = session.createQuery(SELECT);
            employeeList = selectQuery.getResultList();
            System.out.println("Displaying employees. . .");
            Iterator iterator = employeeList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void updateEmployee() {
        Session session = null;
        Employee employee;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            System.out.println("Enter the employee's id you want to update");
            int employeeId = scanner.nextInt();
            employee = session.get(Employee.class, employeeId);
            System.out.println("Choose what column to update:" +
                    "firstName/lastName/dateOfBirth/phoneNumber/email/salary/departmentId");
            String input = scanner.next();
            updateColumns(input);
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            session.close();

            System.out.println("Successfully updated employee with id = " + employee.getId());

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }

    }

    @Override
    public void deleteEmployee() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Employee employee = new Employee();
            System.out.println("Enter employee's id that you want to delete");
            int employeeId = scanner.nextInt();
            employee = session.get(Employee.class, employeeId);
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted employee with id = " + employeeId);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateColumns(String input) {
        Employee employee = new Employee();
        switch (input) {
            case "firstName" -> {
                System.out.println("Enter firstName");
                String newFirstName = scanner.next();
                employee.setFirstName(newFirstName);
            }
            case "lastName" -> {
                System.out.println("Enter lastName");
                String newLastName = scanner.next();
                employee.setLastName(newLastName);
            }
            case "dateOfBirth" -> {
                System.out.println("Enter dateOfBirth (YYYY-MM-DD)");
                String newDateOfBirth = scanner.next();
                employee.setDateOfBirth(newDateOfBirth);
            }
            case "phoneNumber" -> {
                System.out.println("Enter phoneNumber");
                String newPhoneNumber = scanner.next();
                employee.setPhoneNumber(newPhoneNumber);
            }
            case "email" -> {
                System.out.println("Enter email");
                String newEmail = scanner.next();
                employee.setEmail(newEmail);
            }
            case "salary" -> {
                System.out.println("Enter salary");
                int newSalary = scanner.nextInt();
                employee.setSalary(newSalary);
            }
            case "departmentId" -> {
                System.out.println("Enter departmentId");
                Department department = new Department();
                int depId = scanner.nextInt();
                department.setId(depId);
                employee.setDepartment(department);
            }
            default -> System.out.println("Invalid column");
        }
    }
    @Override
    public void buildEmployee(Employee employee){
        Department department = new Department();
        System.out.println("First name: "); String firstName = scanner.next();
        System.out.println("Last name: "); String lastName = scanner.next();
        System.out.println("Date of birth (YYYY-MM-DD): ");String dateOfBirth = scanner.next();
        System.out.println("Phone number: ");String phoneNumber = scanner.next();
        System.out.println("Email: "); String email = scanner.next();
        System.out.println("Salary: "); int salary = scanner.nextInt();
        System.out.println("Department Id: "); int departmentId = scanner.nextInt();
        department.setId(departmentId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setSalary(salary);
        employee.setDepartment(department);
    }
}
