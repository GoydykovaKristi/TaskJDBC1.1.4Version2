package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String GET_ALL = "FROM User";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users;";
    private static final String REMOVE_USER = "DELETE FROM users WHERE id= :id";
    private static final String TRUNCATE_TABLE = "TRUNCATE TABLE users;";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users "+
            "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(45) NOT NULL," +
            "lastname VARCHAR(45) NOT NULL, " +
            "age TINYINT(3) NOT NULL, " +
            "PRIMARY KEY (id));";

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user1 = new User(name, lastName, age);
            session.save(user1);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createQuery(REMOVE_USER)
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }catch (HibernateException e) {
            e.getMessage();
        }catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        }finally {
            if (session != null) {
                session.close();
            }

        }
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
            try {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                users = session.createQuery(GET_ALL).list();

                session.getTransaction().commit();
                session.close();
            }catch (HibernateException e) {
                e.getMessage();
            }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(TRUNCATE_TABLE).executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }

    }
}
