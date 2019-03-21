package com.example.demo.serviceImpl;

import com.example.demo.Enity.MoneyRecord;
import com.example.demo.Enity.Order;
import com.example.demo.Enity.OrderItem;
import com.example.demo.dao.MoneyRecordDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MoneyRecordDao moneyRecordDao;
    @Override
    public Integer insertOrder(Order order) {
       return orderDao.insertOrder(order);
    }

    @Override
    public void changeState(Integer orderID, Integer state) {
        //确认订单
        if(state ==2){
            Order order = orderDao.getOrderByOrderID(orderID);
            userDao.addPoint(order.getUserID(),order.getTotal());
            userDao.changeBalance(order.getUserID(),order.getTotal());

            MoneyRecord moneyRecord = new MoneyRecord();
            moneyRecord.setMoney(order.getTotal()*0.8);
            moneyRecord.setShopID(order.getShopID());
            moneyRecord.setState(0);
            moneyRecordDao.insertMoneyRecord(moneyRecord);

        }
        orderDao.changeState(orderID,state);
    }

    @Override
    public List<Order> getAllOrdersByState(String userID, Integer state) {
        return orderDao.getAllOrdersByState(userID,state);
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderID(Integer ID) {
        return orderDao.getAllOrderItems(ID);
    }

    @Override
    public List<Order> getAllOrdersByShopID(String shopID) {
        return orderDao.getAllOrdersByShopID(shopID);
    }

    @Override
    public List<MoneyRecord> getAllMoneyRecord() {
        return moneyRecordDao.getAllMoneyRecord();
    }




}
