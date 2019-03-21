package com.example.demo.daoImpl;

import com.example.demo.Enity.Requestor;
import com.example.demo.Enity.User;
import com.example.demo.dao.UserDao;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;


@Repository
public class UserDaoImpl implements UserDao{

    public static void main(String[]  args){

       System.out.println(new UserDaoImpl().getAllUsers());
//
//        Set<Address> addresses = new HashSet<>();
//
//        Address address =new Address();
//        address.setAddress("nju");
//        address.setUser(user);
//
//        addresses.add(address);
//
//        user.setAddress(addresses);

//        Student student = new Student();
//        Set<Address> addresses = new HashSet<>();
//        Address address = new Address();
//        address.setAddress("1");
//        addresses.add(address);
//        Address address2 = new Address();
//        address2.setAddress("2");
//        address.setStudent(student);
//        address2.setStudent(student);
//        addresses.add(address2);
//        student.setAddress(addresses);
//        student.setName("lml");
//
//        Session session = HibernateUtils.getSession();
//        session.save(student);
//        HibernateUtils.closeSession(session);

    }
    @Override
    public void insertUser(User user) {
        Session session = HibernateUtils.getSession();
        session.save(user);
        HibernateUtils.closeSession(session);
    }

    @Override
    public void insertRequestor(Requestor requestor) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        session.save(requestor);
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public User getUserForLogin(String userID, String password) {
        Session session = HibernateUtils.getSession();
        Query<User> query = session.createQuery(" from User u where u.id = :n and u.password = :p");
        query.setParameter("n",userID) ;
        query.setParameter("p",password);
        List<User> users = query.list();
        HibernateUtils.closeSession(session);
        if(users.size()>0)
            return users.get(0);
        else
            return null;
    }

    @Override
    public Requestor getRequestorForLogin(String userID, String password) {
        Session session = HibernateUtils.getSession();
        Query<Requestor> query = session.createQuery(" from Requestor r where r.id = :n and r.password = :p" );
        query.setParameter("n",userID);
        query.setParameter("p",password);
        List<Requestor> requestors = query.list();
        HibernateUtils.closeSession(session);
        if(requestors.size()>0)
            return requestors.get(0);
        else
            return null;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtils.getSession();
        Query<User> query = session.createQuery("from User ");
        List<User> users = query.getResultList();
        HibernateUtils.closeSession(session);
        return users;
    }

    @Override
    public List<Requestor> getAllRequestors() {
        Session session = HibernateUtils.getSession();
        Query<Requestor> query = session.createQuery("from Requestor ");
        List<Requestor> requestors = query.getResultList();
        HibernateUtils.closeSession(session);
        return requestors;
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public void addPoint(String userID, double point) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("update User set point = point+ :p where id = :i");
        query.setParameter("p",point);
        query.setParameter("i",userID);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public void changeBalance(String userID, double total) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("update User set total = total - :t where id = :i");
        query.setParameter("t",total);
        query.setParameter("i",userID);
        query.executeUpdate();
        HibernateUtils.closeSession(session);
    }

    @Override
    public int getCount() {
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(*) from User ");
        int result=((Long) query.uniqueResult()).intValue();
        HibernateUtils.closeSession(session);
        return result;
    }
}
