package com.example.demo.service;

import com.example.demo.Enity.Dish;
import java.util.*;

public interface DishService {

    public List<Dish> getAllDishesByShopID(String shopID);
}
