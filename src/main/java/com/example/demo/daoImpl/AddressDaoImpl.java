package com.example.demo.daoImpl;

import com.example.demo.Enity.Address;
import com.example.demo.dao.AddressDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AddressDaoImpl implements AddressDao {
    @Override
    public List<Address> getAllAddressesByUserID(String userID) {
        Session session = HibernateUtils.getSession();
        Query<Address> query = session.createQuery("from Address a where a.user.id = :n");
        query.setParameter("n",userID);
        List<Address> addresses = query.list();
        HibernateUtils.closeSession(session);
        return addresses;
    }
}
