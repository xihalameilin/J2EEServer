package com.example.demo.daoImpl;

import com.example.demo.Enity.ShopInfoChange;
import com.example.demo.dao.ShopInfoChangeDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ShopInfoChangeDaoImpl implements ShopInfoChangeDao {
    @Override
    public void insertInfo(ShopInfoChange shopInfoChange) {
        Session session = HibernateUtils.getSession();
        session.save(shopInfoChange);
        HibernateUtils.closeSession(session);
    }

    @Override
    public List<ShopInfoChange> getAll() {
        Session session = HibernateUtils.getSession();
        Query<ShopInfoChange> query = session.createQuery("from ShopInfoChange where state = 0");
        List<ShopInfoChange> shopInfoChanges = query.getResultList();
        HibernateUtils.closeSession(session);
        return shopInfoChanges;
    }

    @Override
    public void changeState(Integer id, Integer state) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<ShopInfoChange> query = session.createQuery("update ShopInfoChange set state = :s where id= :i");
        query.setParameter("s",state);
        query.setParameter("i",id);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);

    }
}
