package com.example.demo.service;

import com.example.demo.Enity.MoneyRecord;
import com.example.demo.Enity.Order;
import com.example.demo.Enity.OrderItem;

import java.util.*;

public interface OrderService {
    public Integer insertOrder(Order order);

    public void changeState(Integer orderID,Integer state);

    public List<Order> getAllOrdersByState(String userID,Integer state);

    public List<OrderItem> getAllOrderItemsByOrderID(Integer ID);

    public List<Order> getAllOrdersByShopID(String shopID);

    public List<MoneyRecord> getAllMoneyRecord();


}
