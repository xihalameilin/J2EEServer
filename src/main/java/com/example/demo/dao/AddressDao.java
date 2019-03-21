package com.example.demo.dao;

import com.example.demo.Enity.Address;

import java.util.*;
public interface AddressDao {
    public List<Address> getAllAddressesByUserID(String userID);
}
