package dao;

import dao.implementation.ProjectImplementation;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ProjectDao implements ProjectImplementation {
    Scanner scanner = new Scanner(System.in);
    @Override
    public void createProject() {
        Session session = null;
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            Project project = new Project();
            System.out.println("Enter project description");
            String description = scanner.next();
            project.setDescription(description);
            session.beginTransaction();
            session.save(project);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully created project with id = " + project.getId());
        }catch (Exception ex){
            ex.printStackTrace();
            if(session != null){
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void displayProjects() {
        List<Project> projectList;
        try{
            Session session = HibernateUtils.getSessionFactory().openSession();
            String SELECT = "From Project";
            Query selectQuery = session.createQuery(SELECT);
            projectList = selectQuery.getResultList();
            System.out.println("Displaying projects. . .");
            Iterator iterator = projectList.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateProject() {
        Session session = null;
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            Project project = new Project();
            System.out.println("Enter project's id you want to update");
            int projectId = scanner.nextInt();
            session.beginTransaction();
            project = session.get(Project.class,projectId);
            System.out.println("Enter new project description");
            String description = scanner.next();
            project.setDescription(description);
            session.update(project);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully updated project description");
        }catch(Exception ex){
            ex.printStackTrace();
            if(session != null){
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteProject() {
    Session session = null;
    try{
        session = HibernateUtils.getSessionFactory().openSession();
        Project project = new Project();
        System.out.println("Enter project's id you want to delete");
        int projectId = scanner.nextInt();
        project = session.get(Project.class,projectId);
        session.beginTransaction();
        session.delete(project);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted project with id = " + projectId);
    }catch(Exception ex){
        ex.printStackTrace();
        if(session!= null){
            session.getTransaction().rollback();
        }
    }
    }
}
