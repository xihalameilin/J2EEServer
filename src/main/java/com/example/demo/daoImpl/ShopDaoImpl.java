package com.example.demo.daoImpl;

import com.example.demo.Enity.Shop;
import com.example.demo.dao.ShopDao;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.stereotype.Repository;


@Repository
public class ShopDaoImpl implements ShopDao {
    @Override
    public List<Shop> getAllShops() {
        Session session = HibernateUtils.getSession();
        Query<Shop> query = session.createQuery("from Shop where state = 1");
        List<Shop> shops = query.list();
        HibernateUtils.closeSession(session);
        return shops;
    }

    @Override
    public void insertShop(Shop shop) {
        Session session = HibernateUtils.getSession();
        session.save(shop);
        HibernateUtils.closeSession(session);
    }

    @Override
    public Shop getShopByShopID(String shopID) {
        Session session = HibernateUtils.getSession();
        Query<Shop> query = session.createQuery("select Shop from Shop where shopID =:n ");
        query.setParameter("n",shopID);
        List<Shop> shops = query.getResultList();
        HibernateUtils.closeSession(session);
        return shops.get(0);
    }

    @Override
    public void changeState(String shopID, Integer state) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<Shop> query = session.createQuery("update Shop set state = :s where shopID = :n");
        query.setParameter("n",shopID);
        query.setParameter("s",state);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public List<Shop> getAllShopsNew() {
        Session session = HibernateUtils.getSession();
        Query<Shop> query = session.createQuery("from Shop where state = 0");
        List<Shop> shops = query.list();
        HibernateUtils.closeSession(session);
        return shops;
    }

    @Override
    public void updateShop(String shopID, String address, String type) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<Shop> query = session.createQuery("update Shop set address = :a ,type = :t where  shopID = :s");
        query.setParameter("a",address);
        query.setParameter("t",type);
        query.setParameter("s",shopID);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public void changeTotal(String shopID, double total) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<Shop> query = session.createQuery("update Shop set total = total+:t where shopID = :s");
        query.setParameter("t",total);
        query.setParameter("s",shopID);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public int getCount() {
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(*) from Shop ");
        int result=((Long) query.uniqueResult()).intValue();
        HibernateUtils.closeSession(session);
        return result;
    }

    @Override
    public double getTotal() {
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("select sum(total) from Order where state = 1");
        double result=((double) query.uniqueResult());
        HibernateUtils.closeSession(session);
        return result;
    }
}
