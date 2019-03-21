package com.example.demo.serviceImpl;

import com.example.demo.Enity.Shop;
import com.example.demo.Enity.ShopInfoChange;
import com.example.demo.dao.MoneyRecordDao;
import com.example.demo.dao.ShopDao;
import com.example.demo.dao.ShopInfoChangeDao;
import com.example.demo.daoImpl.ShopDaoImpl;
import com.example.demo.service.ShopService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;
    @Autowired
    private MoneyRecordDao moneyRecordDao;

    @Autowired
    private ShopInfoChangeDao shopInfoChangeDao;
    @Override
    public String getShopID() {
        int count = shopDao.getAllShops().size()+1;
        String res = String.valueOf(count);
        int length = res.length();
        for(int i=1;i<=7-length;i++){
            res="0"+res;
        }
        return res;
    }

    @Override
    public List<Shop> getAllShops() {
        return shopDao.getAllShops();
    }

    @Override
    public void registerShop(Shop shop) {
        shopDao.insertShop(shop);
    }

    @Override
    public Shop getShopByShopID(String shopID) {
        return shopDao.getShopByShopID(shopID);
    }

    @Override
    public List<Shop> getAllShopsNew() {
        return shopDao.getAllShopsNew();
    }

    @Override
    public void changeShopState(String shopID, Integer state) {
        shopDao.changeState(shopID,state);
    }

    @Override
    public void insertInfo(ShopInfoChange shopInfoChange) {
        shopInfoChangeDao.insertInfo(shopInfoChange);
    }

    @Override
    public List<ShopInfoChange> getAllShopInfoChange() {
        return shopInfoChangeDao.getAll();
    }

    @Override
    public void changeShopInfoState(Integer id, Integer state,String address,String type,String shopID) {
        if(state == 1){
            shopDao.updateShop(shopID,address,type);
        }
        shopInfoChangeDao.changeState(id,state);
    }

    @Override
    public void changeMoneyRecordState(Integer id, Integer state,double total,String shopID) {
        if(state == 1){
            shopDao.changeTotal(shopID,total);
        }
       moneyRecordDao.changeRecordState(id,state);
    }
}
