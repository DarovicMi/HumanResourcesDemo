package utils;


import entity.Department;
import entity.Employee;
import entity.Project;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/human_resources");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "*****");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.HBM2DDL_AUTO, "validate");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Department.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Project.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void shutdown(){
        getSessionFactory().close();
    }
}
