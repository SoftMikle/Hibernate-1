import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.EntityGenerator;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Transaction transaction = null;
        int numberOfEmployees = 100;
        List<Employee> employees = EntityGenerator.generateEntities(numberOfEmployees);
        try(final Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            employees.forEach(session::save);

            transaction.commit();
            System.out.println("Transaction finished Successful");
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try(final Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            employees = new ArrayList<Employee>(20);
            //int counter = (int)Math.random()*80;
            for (int i = 1; i < 101; i++) {
                employees.add(session.get(Employee.class, i));
            }
            transaction.commit();
            employees.forEach(System.out::println);
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
