package ma.projet.service;

import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entity.User; 
import ma.projet.util.HibernateUtil;
import ma.projet.util.Utils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author safiya
 */

public class UserService implements IDao<User> {  

    @Override
    public boolean create(User o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            o.setPassword(Utils.MD5(o.getPassword()));
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(User o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            o.setPassword(Utils.MD5(o.getPassword()));
            session.update(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(User o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = null; 
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            users = session.createQuery("from User").list();
            tx.commit();
            return users; 
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return users;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            user = (User) session.get(User.class, id);  // Update to User
            tx.commit();
            return user;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return user;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User getByEmail(String email) {
        User user = null; 
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            user = (User) session.createQuery("from User u where u.email = :email")
                .setParameter("email", email)
                .uniqueResult();
            tx.commit();
            return user; 
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return user;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
