package com.example.demo.dao;

import com.example.demo.Enity.MoneyRecord;
import java.util.*;

public interface MoneyRecordDao {

    public void insertMoneyRecord(MoneyRecord moneyRecord);

    public List<MoneyRecord> getAllMoneyRecord();

    public void changeRecordState(Integer id,Integer state);
}
