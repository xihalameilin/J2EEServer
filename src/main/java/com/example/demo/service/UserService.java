package com.example.demo.service;

import com.example.demo.Enity.Requestor;
import com.example.demo.Enity.User;

public interface UserService {
    public void registerUser(User user);

    public void registerRequestor(Requestor requestor);

    public String login(String userID,String password);

    public boolean isValid(String userID);

    public boolean isEmailValid(String email);

    public User getUserByID(String userID);

    public void updateUser(User user);

    public void addPoint(String userID,double point);
}
