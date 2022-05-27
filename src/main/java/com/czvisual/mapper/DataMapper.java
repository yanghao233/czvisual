package com.czvisual.mapper;

import com.czvisual.entity.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {
    int insertData(Data data, String table);

    int insertDataBatch(List<Data> dataList, String table);

    List<Data> getAllData(String startDate, String endDate, String table);
    List<Data> getRealAllData(String table);
    int updateData(Data data,String table);

    int delDataById(Integer id,String table);
}
