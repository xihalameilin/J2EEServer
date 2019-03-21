package com.example.demo.serviceImpl;

import com.example.demo.Enity.Requestor;
import com.example.demo.Enity.User;
import com.example.demo.costant.DefaultConstant;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public void registerUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void registerRequestor(Requestor requestor) {
        userDao.insertRequestor(requestor);
    }

    @Override
    public String login(String userID, String password) {
        User user = userDao.getUserForLogin(userID,password);
        Requestor requestor = userDao.getRequestorForLogin(userID,password);
        if(userID.equals("admin") && password.equals("admin")){
            return DefaultConstant.ADMIN_IDENTITY;
        }
        else if(null != user){
            return DefaultConstant.USER_IDENTITY;
        }
        else if(null != requestor){
            return DefaultConstant.REQUESTOR_IDENTITY ;
        }
        else {
            return DefaultConstant.LOGIN_FAILURE;
        }
    }

    @Override
    public boolean isValid(String userID) {
        List<User> users = userDao.getAllUsers();
        List<Requestor> requestors = userDao.getAllRequestors();
        for(User user:users){
            if(user.getId().equals(userID))
                return false;
        }
        for(Requestor requestor:requestors){
            if(requestor.getId().equals(userID))
                return false;
        }
        return true;
    }

    @Override
    public boolean isEmailValid(String email) {
        List<User> users = userDao.getAllUsers();
        List<Requestor> requestors = userDao.getAllRequestors();
        for(User user:users){
            if(user.getEmail().equals(email))
                return false;
        }

        for(Requestor requestor:requestors){
            if(requestor.getEmail().equals(email))
                return false;
        }
        return true;
    }

    @Override
    public User getUserByID(String userID) {
        List<User> users = userDao.getAllUsers();
        for(User user:users){
            if (user.getId().equals(userID))
                return user;
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void addPoint(String userID, double point) {
        userDao.addPoint(userID,point);
    }


}
