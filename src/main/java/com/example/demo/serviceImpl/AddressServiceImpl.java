package com.example.demo.serviceImpl;

import com.example.demo.Enity.Address;
import com.example.demo.dao.AddressDao;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressDao addressDao;
    @Override
    public List<Address> getAllAddressesByUserID(String userID) {
        return addressDao.getAllAddressesByUserID(userID);
    }
}
