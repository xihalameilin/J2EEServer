package com.example.demo.dao;

import com.example.demo.Enity.Requestor;
import com.example.demo.Enity.User;
import java.util.*;

public interface UserDao {

    public void insertUser(User user);

    public void insertRequestor(Requestor requestor);

    public User getUserForLogin(String userID,String password);

    public Requestor getRequestorForLogin(String userID,String password);

    public List<User> getAllUsers();

    public List<Requestor> getAllRequestors();

    public void update(User user);

    public void addPoint(String userID,double point);

    public void changeBalance(String userID,double total);

    public int getCount();
}
