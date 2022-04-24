package dao;

import dao.implementation.DepartmentImplementation;
import entity.Department;
import org.hibernate.Session;
import utils.HibernateUtils;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DepartmentDao implements DepartmentImplementation {
    Scanner scanner = new Scanner(System.in);

    public void createDepartment() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Department department = new Department();
            System.out.print("Enter department name ");
            String departmentName = scanner.nextLine();
            department.setName(departmentName);
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
            System.out.println("Successfully created department with id = "
                    + department.getId() + " ,name = " + department.getName());
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    public void displayDepartments() {
        List<Department> listOfDepartments;
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            String SELECT = "FROM Department";
            Query disposeDepartments = session.createQuery(SELECT);
            listOfDepartments = disposeDepartments.getResultList();
            System.out.println("Displaying list of departments ...");
            for (Department listOfDepartment : listOfDepartments) {
                System.out.println(listOfDepartment);
            }
            session.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateDepartment() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            System.out.print("Enter the department id you want to update ");
            Department department = new Department();
            int departmentId = scanner.nextInt();
            session.beginTransaction();
            department = session.get(Department.class, departmentId);
            System.out.println("Enter the new department name ");
            String new_department_name = scanner.next();
            department.setName(new_department_name);
            session.update(department);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully updated department with id = " + departmentId);

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    public void deleteDepartment() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Department department = new Department();
            System.out.println("Enter the department id you want to delete");
            int departmentId = scanner.nextInt();
            department = session.get(Department.class, departmentId);
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted department with id = " + departmentId);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }
}
