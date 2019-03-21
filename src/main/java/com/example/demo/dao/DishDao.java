package com.example.demo.dao;


import com.example.demo.Enity.Dish;
import java.util.*;

public interface DishDao {
    public List<Dish> getAllDishByShopID(String shopID);
}
