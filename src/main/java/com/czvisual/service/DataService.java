package com.czvisual.service;

import com.czvisual.entity.Data;
import com.czvisual.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataMapper dataMapper;

    public int insertData(Data data, String table) {
        return dataMapper.insertData(data, table);
    }

    public int insertDataBatch(List<Data> dataList, String table) {
        return dataMapper.insertDataBatch(dataList, table);
    }
}
