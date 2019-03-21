package com.example.demo.daoImpl;

import com.example.demo.Enity.MoneyRecord;
import com.example.demo.dao.MoneyRecordDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoneyRecordDaoImpl implements MoneyRecordDao {
    @Override
    public void insertMoneyRecord(MoneyRecord moneyRecord) {
        Session session = HibernateUtils.getSession();
        session.save(moneyRecord);
        HibernateUtils.closeSession(session);
    }

    @Override
    public List<MoneyRecord> getAllMoneyRecord() {
        Session session = HibernateUtils.getSession();
        Query<MoneyRecord> query = session.createQuery("from MoneyRecord where state = 0");
        List<MoneyRecord> moneyRecords = query.getResultList();
        HibernateUtils.closeSession(session);
        return moneyRecords;
    }

    @Override
    public void changeRecordState(Integer id, Integer state) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<MoneyRecord> query = session.createQuery("update MoneyRecord set state = :s where id = :i" );
        query.setParameter("s",state);
        query.setParameter("i",id);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }
}
