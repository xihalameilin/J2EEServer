package com.example.demo.dao;

import com.example.demo.Enity.Order;
import com.example.demo.Enity.OrderItem;

import java.util.*;

public interface OrderDao {

    public Integer insertOrder(Order order);

    public void changeState(Integer orderID,Integer state);

    public List<Order> getAllOrdersByState(String userID,Integer state);

    public List<OrderItem> getAllOrderItems(Integer ID);

    public Order getOrderByOrderID(Integer orderID);

    public List<Order> getAllOrdersByShopID(String shopID);
}
