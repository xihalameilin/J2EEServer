package com.example.demo.service;

import com.example.demo.Enity.Address;
import java.util.*;

public interface AddressService {
    public List<Address> getAllAddressesByUserID(String userID);
}
