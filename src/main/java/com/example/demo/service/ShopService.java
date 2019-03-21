package com.example.demo.service;

import com.example.demo.Enity.Shop;
import com.example.demo.Enity.ShopInfoChange;

import java.util.*;

public interface ShopService {
    public String getShopID();

    public List<Shop> getAllShops();

    public void registerShop(Shop shop);

    public Shop getShopByShopID(String shopID);

    public List<Shop> getAllShopsNew();

    public void changeShopState(String shopID,Integer state);

    public void insertInfo(ShopInfoChange shopInfoChange);

    public List<ShopInfoChange> getAllShopInfoChange();

    public void changeShopInfoState(Integer id,Integer state,String address,String type,String shopID);

    public void changeMoneyRecordState(Integer id,Integer state,double total,String shopID);
}
