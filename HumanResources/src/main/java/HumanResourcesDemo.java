import entity.human.resources.input.HumanResources;
import utils.HibernateUtils;


public class HumanResourcesDemo {
    public static void main(String[] args) {
        HumanResources humanResources = new HumanResources();
        humanResources.displayEntities();
        
      //  HibernateUtils.shutdown();

    }
}
